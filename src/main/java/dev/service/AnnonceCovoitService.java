package dev.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.domain.AnnonceCovoit;
import dev.domain.Collegue;
import dev.domain.Itineraire;
import dev.domain.Vehicule;
import dev.repository.AnnonceRepo;

@Service
public class AnnonceCovoitService {

	@Autowired
	private AnnonceRepo annonceRepo;

	public AnnonceCovoit ajouterUneAnnonce(Collegue conducteur, Itineraire itineraire, Vehicule vehicule, LocalDateTime dateDeDepart) {

		return annonceRepo.save(new AnnonceCovoit(conducteur, itineraire, vehicule, dateDeDepart));

	}

}
