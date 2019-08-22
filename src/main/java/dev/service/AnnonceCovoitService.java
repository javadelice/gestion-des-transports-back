package dev.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.domain.AnnonceCovoit;
import dev.domain.Itineraire;
import dev.domain.Vehicule;
import dev.repository.AnnonceRepo;

@Service
public class AnnonceCovoitService {

	@Autowired
	private AnnonceRepo annonceRepo;

	public AnnonceCovoit ajouterUneAnnonce(Itineraire itineraire, Vehicule vehicule, LocalDate dateDeDepart, LocalTime heureDeDepart) {

		return annonceRepo.save(new AnnonceCovoit(itineraire, vehicule, dateDeDepart, heureDeDepart));

	}

}
