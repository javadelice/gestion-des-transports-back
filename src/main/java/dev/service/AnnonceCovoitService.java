package dev.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import dev.domain.AnnonceCovoit;
import dev.domain.Collegue;
import dev.domain.InfosVille;
import dev.domain.Itineraire;
import dev.domain.ReservationCovoit;
import dev.domain.Statut;
import dev.domain.Vehicule;
import dev.dto.InfoCovoit;
import dev.exception.AdresseNonTrouveeException;
import dev.exception.AnnonceInvalidException;
import dev.repository.AnnonceCovoitRepo;
import dev.repository.AnnonceRepo;
import dev.repository.CollegueRepo;
import dev.repository.InfosVilleRepo;
import dev.repository.ItineraireRepo;
import dev.repository.ReservationCovoitRepo;
import dev.repository.VehiculeRepo;

@Service
public class AnnonceCovoitService {

	@Autowired
	private AnnonceRepo annonceRepo;

	@Autowired
	private ItineraireRepo itiRepo;

	@Autowired
	private VehiculeRepo vehiRepo;

	@Autowired
	private CollegueRepo collegueRepo;

	@Autowired
	private AnnonceCovoitRepo annonceCoRepo;

	@Autowired
	private ReservationCovoitRepo reservationRepo;

	@Autowired
	private InfosVilleRepo infosVilleRepo;

	@Autowired
	private JavaMailSender javaMailSender;

	private static final int PLACE_MINIMUM_DISPONIBLE = 1;
	private static final int PLACE_MAXIMUM_DISPONIBLE = 20;
	private static final double VITESSE_MOYENNE_TRAJET = 70.0;

	public void verifierInfos(InfoCovoit infoCo, LocalDateTime dateTime, String email) throws MessagingException {
		Map<String, String> erreurs = new HashMap<>();

		if (dateTime.isBefore(LocalDateTime.now())) {
			erreurs.put("dateDebut", "La date ne peut être antérieure à aujourd'hui");
		}

		if (infoCo.getNbPlaceDispo() > PLACE_MAXIMUM_DISPONIBLE || infoCo.getNbPlaceDispo() < PLACE_MINIMUM_DISPONIBLE) {
			erreurs.put("nbPlaceDispo", "Le nombre de places doit être compris entre 1 et 20.");
		}

		if (infoCo.getImmatriculation() != null) {
			Pattern p = Pattern.compile("[A-Z]{2}-[0-9]{3}-[A-Z]{2}");
            Matcher m = p.matcher(infoCo.getImmatriculation().toUpperCase());
            if (!m.matches()) erreurs.put("immatriculation", "Immatriculation invalide");
            else {
                Optional<Vehicule> vehiculeE = this.vehiRepo.getVehiculeByImmatriculation(infoCo.getImmatriculation());
                vehiculeE.ifPresent(vehicule1 -> erreurs.put("immatriculation","Immatriculation déjà enregistrée"));
            }
		}

		if (!erreurs.isEmpty()) {
			throw new AnnonceInvalidException(erreurs);
		}
		if (dateTime != null
		        && dateTime.isAfter(LocalDateTime.now())
		        && infoCo.getMarque() != null
		        && infoCo.getModele() != 0) {

			Itineraire itineraire = calculItineraire(infoCo.getAdresseDepart(), infoCo.getAdresseDestination());
			Vehicule vehicule = new Vehicule(infoCo.getImmatriculation().toUpperCase(), infoCo.getMarque(), infoCo.getModele(),
			        infoCo.getNbPlaceDispo());

			this.ajouterUneAnnonce(email, itineraire, vehicule, dateTime);

		}

	}

	public void ajouterUneAnnonce(String email, Itineraire itineraire, Vehicule vehicule, LocalDateTime dateTime) {

		Optional<Collegue> collegueConnecte = this.collegueRepo.findByEmail(email);

		collegueConnecte.ifPresent(conducteur -> {
			itiRepo.save(itineraire);
			vehiRepo.save(vehicule);
			annonceCoRepo.save(new AnnonceCovoit(conducteur, itineraire, vehicule, dateTime));
		});
	}

	public List<AnnonceCovoit> getAnnoncesEnCours(String email) {

		Optional<Collegue> collegueConnecte = this.collegueRepo.findByEmail(email);

		Collegue col = collegueConnecte.get();
		return this.annonceCoRepo.findAll().stream().filter(annonce -> annonce.getConducteur().equals(col))
		        .filter(annonce -> annonce.getDateTime().isAfter(LocalDateTime.now()) && annonce.getStatut().equals(Statut.STATUT_ENCOURS))
		        .collect(Collectors.toList());
	}

	public List<AnnonceCovoit> getAnciennesAnnonces(String email) {

		Optional<Collegue> collegueConnecte = this.collegueRepo.findByEmail(email);

		Collegue col = collegueConnecte.get();
		return this.annonceCoRepo.findAll().stream().filter(annonce -> annonce.getConducteur().equals(col))
		        .filter(annonce -> annonce.getDateTime().isBefore(LocalDateTime.now()) || annonce.getStatut().equals(Statut.STATUT_ANNULEE))
		        .collect(Collectors.toList());

	}

	public int getNbPassagers(AnnonceCovoit annonceCovoit) {
		Optional<List<ReservationCovoit>> optCovoitList = this.reservationRepo.getAllByAnnonceCovoit(annonceCovoit);

		int nbPassager = 0;

		List<ReservationCovoit> resaList = new ArrayList<>();
		optCovoitList.ifPresent(covoitList -> {
			for (ReservationCovoit resa : covoitList) {

				if (resa.getStatut().equals(Statut.STATUT_ENCOURS) || resa.getStatut().equals(Statut.STATUT_TERMINEE)) {
					resaList.add(resa);
				}
			}
		});

		nbPassager = resaList.size();
		return nbPassager;
	}

	public void annonceAnnulee(String email, AnnonceCovoit annonceCo) throws MessagingException {

		AnnonceCovoit annonceAnnulee = this.annonceCoRepo.getAnnonceById(annonceCo.getId());
		annonceAnnulee.setStatut(Statut.STATUT_ANNULEE);
		annonceCoRepo.save(annonceAnnulee);

		sendAnnulationConducteur(email, annonceAnnulee);

		Optional<List<ReservationCovoit>> listPassagersCovoit = this.reservationRepo.getReservationCovoitsByAnnonceCovoit(annonceCo);
		listPassagersCovoit.ifPresent(covoitList -> {
			for (ReservationCovoit resa : covoitList) {
				sendAnnulationPassagers(resa.getPassagers().getEmail(), annonceCo);
				resa.setStatut(Statut.STATUT_ANNULEE);
				this.reservationRepo.save(resa);
			}
		});
	}

	public void sendAnnulationPassagers(String email, AnnonceCovoit annonceAnnulee) {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd MMMM yyyy");

		try {
			helper = new MimeMessageHelper(message, true);
			helper.setTo(email);
			helper.setSubject("Annulation de votre covoiturage - " + annonceAnnulee.getItineraire().getAdresseDepart() + " --> "
			        + annonceAnnulee.getItineraire().getAdresseDest());
			helper.setText("<h1>Annulation de votre covoiturage du " + annonceAnnulee.getDateTime().format(formatDate) + "</h1>", true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		javaMailSender.send(message);
	}

	public void sendAnnulationConducteur(String emailDestinataire, AnnonceCovoit annonceAnnulee) throws MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd MMMM yyyy");

		helper.setTo(emailDestinataire);
		helper.setSubject("Confirmation annulation du covoiturage - " + annonceAnnulee.getItineraire().getAdresseDepart() + " --> "
		        + annonceAnnulee.getItineraire().getAdresseDest());
		helper.setText("<h1> Bonjour " + annonceAnnulee.getConducteur().getNom() + " " + annonceAnnulee.getConducteur().getPrenom() + ", </h1>" +
		        "<p> Suite à votre demande, nous vous confirmons l'annulation de votre covoiturage du " + " <strong>"
		        + annonceAnnulee.getDateTime().format(formatDate) + " </br> "
		        + annonceAnnulee.getItineraire().getAdresseDepart() + " --> " + annonceAnnulee.getItineraire().getAdresseDest() + "</strong> "
		        + "<p>", true);

		javaMailSender.send(message);

	}

	public List<AnnonceCovoit> getAnnoncesCovoitParLieuDepart(String lieuDepart){
		return this.annonceCoRepo.getAnnonceCovoitsByItineraire_AdresseDepart(lieuDepart);
	}

	public List<AnnonceCovoit> getAnnoncesCovoitParLieuDepartAndLieuArrive(String lieuDepart,String lieuArrive){
		return this.annonceCoRepo.getAnnonceCovoitsByItineraire_AdresseDepartAndItineraire_AdresseDest(lieuDepart,lieuArrive);
	}

	public Itineraire calculItineraire(String adresseDepart, String adresseDest) {

		InfosVille villeDep = this.infosVilleRepo.getInfosVilleByVille(adresseDepart.toUpperCase()).orElseThrow(() -> new AdresseNonTrouveeException("L'adresse de départ est une adresse inconnue."));
		InfosVille villeDest = this.infosVilleRepo.getInfosVilleByVille(adresseDest.toUpperCase()).orElseThrow(() -> new AdresseNonTrouveeException("L'adresse de destination est une adresse inconnue."));

		double calculDistance = calculDistanceTrajet(villeDep.getLatitude(), villeDep.getLongitude(), villeDest.getLatitude(),
		        villeDest.getLongitude(), "K");

		int distance = (int) calculDistance;
		String trajet = calculDureeTrajet(calculDistance);

		Itineraire infosVille = new Itineraire(adresseDepart, adresseDest, trajet, distance);

		return infosVille;

	}

	private static double calculDistanceTrajet(double lat1, double lon1, double lat2, double lon2, String unit) {

		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		} else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
			        + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			if (unit == "K") {
				dist = dist * 1.609344;
			} else if (unit == "N") {
				dist = dist * 0.8684;
			}
			return (dist);
		}
	}

	private static String calculDureeTrajet(double distanceTrajet) {
		double dureeTrajet = (distanceTrajet / VITESSE_MOYENNE_TRAJET) * 60;
		long v1 = (long) dureeTrajet;
		String date = v1 / 60 + "h" + v1 % 60;
		return (date);
	}

}
