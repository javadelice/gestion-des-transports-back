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
import dev.dto.InfoCovoit;
import dev.dto.CollegueDTO;
import dev.dto.ListeAnnonceCovoitDTO;
import dev.exception.AnnonceInvalidException;
import dev.service.AnnonceCovoitService;

@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping("/collaborateur")
public class AnnonceCovoitController {

	@Autowired
	AnnonceCovoitService annonceService;

	@RequestMapping(method = RequestMethod.POST, path = "/annonces/creer")
	public void addAnnonceCovoit(@RequestBody InfoCovoit infoCo) throws AnnonceInvalidException {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		LocalDate dateDeDepart = LocalDate.parse(infoCo.getDateDeDepart(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String infoHoraire = infoCo.getHeureDeDepart() + ":" + infoCo.getMinuteDeDepart();
		LocalTime horaireDeDepart = LocalTime.parse(infoHoraire, DateTimeFormatter.ofPattern("HH:mm"));
		LocalDateTime dateTime = LocalDateTime.of(dateDeDepart, horaireDeDepart);
		
		this.annonceService.verifierInfos(infoCo, dateTime, email);
		
	}

	@RequestMapping(method = RequestMethod.GET, path = "/annonces")
	public List<ListeAnnonceCovoitDTO> getListAnnonces() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		List<AnnonceCovoit> annonceCovoitList = this.annonceService.getAnnoncesEnCours(email);
		return annonceCovoitList.stream()
		        .map(annonce -> {
			        ListeAnnonceCovoitDTO annonceCovoitDTO = new ListeAnnonceCovoitDTO();
			        annonceCovoitDTO.setId(annonce.getId());
			        annonceCovoitDTO.setCollegue(new CollegueDTO(annonce.getConducteur()));
			        annonceCovoitDTO.setItineraire(annonce.getItineraire());
			        annonceCovoitDTO.setVehicule(annonce.getVehicule());
			        annonceCovoitDTO.setDateTime(annonce.getDateTime());
			        annonceCovoitDTO.setNbVoyageurs(this.annonceService.getNbPassagers(annonce));
			        return annonceCovoitDTO;
		        })
		        .collect(Collectors.toList());
	}

	@RequestMapping(method = RequestMethod.GET, path = "/annonces_old")
	public List<ListeAnnonceCovoitDTO> getOldListAnnonces() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		List<AnnonceCovoit> annonceCovoitList = this.annonceService.getAnciennesAnnonces(email);
		return annonceCovoitList.stream()
		        .map(annonce -> {
			        ListeAnnonceCovoitDTO annonceCovoitDTO = new ListeAnnonceCovoitDTO();
			        annonceCovoitDTO.setId(annonce.getId());
			        annonceCovoitDTO.setCollegue(new CollegueDTO(annonce.getConducteur()));
			        annonceCovoitDTO.setItineraire(annonce.getItineraire());
			        annonceCovoitDTO.setVehicule(annonce.getVehicule());
			        annonceCovoitDTO.setDateTime(annonce.getDateTime());
			        annonceCovoitDTO.setNbVoyageurs(this.annonceService.getNbPassagers(annonce));
			        return annonceCovoitDTO;
		        })
		        .collect(Collectors.toList());
	}
}
