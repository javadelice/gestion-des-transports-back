package dev.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.AnnonceCovoit;
import dev.domain.Collegue;
import dev.domain.Itineraire;
import dev.domain.Vehicule;
import dev.service.AnnonceCovoitService;

@RestController
@RequestMapping("/annonces")
public class AnnonceCovoitController {

	@Autowired
	AnnonceCovoitService annonceService;

	@RequestMapping(method = RequestMethod.POST, path = "/créer")
	public AnnonceCovoit addAnnonceCovoit(@RequestBody Collegue conducteur, Itineraire itineraire, Vehicule vehicule, LocalDateTime dateDeDepart) {
		return annonceService.ajouterUneAnnonce(conducteur, itineraire, vehicule, dateDeDepart);
	}

}
