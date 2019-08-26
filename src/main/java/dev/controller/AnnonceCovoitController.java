package dev.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.AnnonceCovoit;
import dev.domain.InfoCovoit;
import dev.domain.Itineraire;
import dev.domain.Vehicule;
import dev.dto.AnnonceCovoitDTO;
import dev.dto.CollegueDTO;
import dev.service.AnnonceCovoitService;

@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping("/collaborateur")
public class AnnonceCovoitController {

	@Autowired
	AnnonceCovoitService annonceService;

	@RequestMapping(method = RequestMethod.POST, path = "/annonces/creer")
	public void addAnnonceCovoit(@RequestBody InfoCovoit infoCo) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Itineraire itineraire = new Itineraire(infoCo.getAdresseDepart(), infoCo.getAdresseDestination(), infoCo.getDuree(), infoCo.getDistance());
		Vehicule vehicule = new Vehicule(infoCo.getImmatriculation(), infoCo.getMarque(), infoCo.getModele(), infoCo.getNbPlaceDispo());
		LocalDate dateDeDepart = LocalDate.parse(infoCo.getDateDeDepart(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String infoHoraire = infoCo.getHeureDeDepart() + ":" + infoCo.getMinuteDeDepart();
		LocalTime horaireDeDepart = LocalTime.parse(infoHoraire, DateTimeFormatter.ofPattern("HH:mm"));
		LocalDateTime dateTime = LocalDateTime.of(dateDeDepart, horaireDeDepart);

		annonceService.ajouterUneAnnonce(email, itineraire, vehicule, dateTime);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/annonces")
	public List<AnnonceCovoitDTO> getListAnnonces () {
		//Je récupère le conducteur connecté
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		// Je créé une liste avec les éléments d'une annonce
        List<AnnonceCovoit> annonceCovoitList = this.annonceService.getAnnoncesEnCours(email);
        return annonceCovoitList.stream()
                .map(annonce->{
                    AnnonceCovoitDTO annonceCovoitDTO = new AnnonceCovoitDTO();
                    annonceCovoitDTO.setId(annonce.getId());
                    annonceCovoitDTO.setCollegue(new CollegueDTO(annonce.getConducteur()));
                    annonceCovoitDTO.setItineraire(annonce.getItineraire());
                    annonceCovoitDTO.setVehicule(annonce.getVehicule());
                    annonceCovoitDTO.setDateTime(annonce.getDateTime());
                    return annonceCovoitDTO;
                })
                .collect(Collectors.toList());	
        }
	
	@RequestMapping(method = RequestMethod.GET, path = "/annonces_old")
	public List<AnnonceCovoitDTO> getOldListAnnonces(){
		String email = SecurityContextHolder.getContext().getAuthentication().getName(); 
		
		List <AnnonceCovoit> annonceCovoitList = this.annonceService.getAnciennesAnnonces(email); 
		 return annonceCovoitList.stream()
	                .map(annonce->{
	                    AnnonceCovoitDTO annonceCovoitDTO = new AnnonceCovoitDTO();
	                    annonceCovoitDTO.setId(annonce.getId());
	                    annonceCovoitDTO.setCollegue(new CollegueDTO(annonce.getConducteur()));
	                    annonceCovoitDTO.setItineraire(annonce.getItineraire());
	                    annonceCovoitDTO.setVehicule(annonce.getVehicule());
	                    annonceCovoitDTO.setDateTime(annonce.getDateTime());
	                    return annonceCovoitDTO;
	                })
	                .collect(Collectors.toList());	
	}
	
}
