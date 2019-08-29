package dev.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import dev.domain.AnnonceCovoit;
import dev.domain.Collegue;
import dev.domain.Itineraire;
import dev.domain.ReservationCovoit;
import dev.domain.Statut;
import dev.domain.Vehicule;
import dev.dto.InfoCovoit;
import dev.exception.AnnonceInvalidException;
import dev.repository.AnnonceCovoitRepo;
import dev.repository.AnnonceRepo;
import dev.repository.CollegueRepo;
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
	private JavaMailSender javaMailSender;

	private static final int PLACE_MINIMUM_DISPONIBLE = 1;
	private static final int PLACE_MAXIMUM_DISPONIBLE = 20;

	public void verifierInfos(InfoCovoit infoCo, LocalDateTime dateTime, String email) throws MessagingException {
		Map<String, String> erreurs = new HashMap<>();

		if (dateTime.isBefore(LocalDateTime.now())) {
			erreurs.put("dateDebut", "La date ne peut être antérieure à aujourd'hui");
		}

		if (infoCo.getNbPlaceDispo() > PLACE_MAXIMUM_DISPONIBLE || infoCo.getNbPlaceDispo() < PLACE_MINIMUM_DISPONIBLE) {
			erreurs.put("nbPlaceDispo", "Le nombre de places doit être compris entre 1 et 20.");
		}

		if (!erreurs.isEmpty()) {
			throw new AnnonceInvalidException(erreurs);
		}
		if (dateTime != null
		        && dateTime.isAfter(LocalDateTime.now())
		        && infoCo.getMarque() != null
		        && infoCo.getImmatriculation() != null
		        && infoCo.getModele() != 0) {

			Itineraire itineraire = new Itineraire(infoCo.getAdresseDepart(), infoCo.getAdresseDestination(), infoCo.getDuree(),
			        infoCo.getDistance());
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
		List<AnnonceCovoit> optionalCovoitList = new ArrayList<>();

		Collegue col = collegueConnecte.get();
		optionalCovoitList = this.annonceCoRepo.findAll().stream().filter(annonce -> annonce.getConducteur().equals(col))
		        .filter(annonce -> annonce.getDateTime().isAfter(LocalDateTime.now()))
		        .collect(Collectors.toList());
		return optionalCovoitList;
	}

	public List<AnnonceCovoit> getAnciennesAnnonces(String email) {

		Optional<Collegue> collegueConnecte = this.collegueRepo.findByEmail(email);
		List<AnnonceCovoit> optionalCovoitList = new ArrayList<>();

		Collegue col = collegueConnecte.get();
		optionalCovoitList = this.annonceCoRepo.findAll().stream().filter(annonce -> annonce.getConducteur().equals(col))
		        .filter(annonce -> annonce.getDateTime().isBefore(LocalDateTime.now()))
		        .collect(Collectors.toList());
		return optionalCovoitList;
	}

	public int getNbPassagers(AnnonceCovoit annonceCovoit) {
		Optional<List<ReservationCovoit>> optCovoitList = this.reservationRepo.getAllByAnnonceCovoit(annonceCovoit);

		int nbPassager = 0;

		List<ReservationCovoit> resaList = new ArrayList<>();
		optCovoitList.ifPresent(covoitList -> {
			for (ReservationCovoit resa : covoitList) {
				resaList.add(resa);
			}
		});
		nbPassager = resaList.size();
		return nbPassager;
	}

	public void annonceAnnulee(String email, AnnonceCovoit annonceCo) throws MessagingException {

		AnnonceCovoit annonceAnnulee = this.annonceCoRepo.getAnnonceById(annonceCo.getId());
		annonceAnnulee.setStatut(Statut.STATUT_ANNULEE);
		annonceCoRepo.save(annonceAnnulee);

		sendAnnulationConducteur(email, annonceCo);

		Optional<List<ReservationCovoit>> listPassagersCovoit = this.reservationRepo.getReservationCovoitsByAnnonceCovoit(annonceCo);
		listPassagersCovoit.ifPresent(covoitList -> {
			for (ReservationCovoit resa : covoitList) {
				sendAnnulationPassagers(resa.getPassagers().getEmail(), annonceCo);
			}
		});
	}

	public void sendAnnulationPassagers(String email, AnnonceCovoit annonceAnnulee) {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setTo(email);
			helper.setSubject("Annulation de votre covoiturage - " + annonceAnnulee.getItineraire().getAdresseDepart());
			helper.setText("<h1>Annulation de votre covoiturage du " + annonceAnnulee.getDateTime() + "</h1>", true);
		} 
			catch (MessagingException e) {
			e.printStackTrace();
		}

		javaMailSender.send(message);

	}

	public void sendAnnulationConducteur(String emailDestinataire, AnnonceCovoit annonceAnnulee) throws MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(emailDestinataire);
		helper.setSubject("Confirmation annulation du covoiturage - " + annonceAnnulee.getItineraire().getAdresseDepart());
		helper.setText("<h1>Confirmation de création de votre covoiturage du " + annonceAnnulee.getDateTime() + "</h1>", true);

		javaMailSender.send(message);

	}

}
