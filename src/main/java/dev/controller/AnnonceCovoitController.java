package dev.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.AnnonceCovoit;
import dev.domain.InfoCovoit;
import dev.domain.Itineraire;
import dev.domain.Vehicule;
import dev.service.AnnonceCovoitService;

@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping("/annonces")
public class AnnonceCovoitController {

	@Autowired
	AnnonceCovoitService annonceService;

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public AnnonceCovoit addAnnonceCovoit(@RequestBody InfoCovoit infoCo) {

		Itineraire itineraire = new Itineraire(infoCo.getAdresseDepart(), infoCo.getAdresseDestination(), infoCo.getDureeDeTrajet(),
		        infoCo.getDistance());
		Vehicule vehicule = new Vehicule(infoCo.getImmatriculation(), infoCo.getMarque(), infoCo.getModele(), infoCo.getNombrePassager());

		LocalDate dateDeDepart = LocalDate.parse(infoCo.getDateDeDepart(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		LocalTime heureDeDepart = LocalTime.parse(infoCo.getHeureDeDepart(), DateTimeFormatter.ofPattern("HH:mm"));

		return annonceService.ajouterUneAnnonce(itineraire, vehicule, dateDeDepart, heureDeDepart);
	}
}