package dev.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.domain.AnnonceCovoit;
import dev.domain.Collegue;
import dev.domain.Itineraire;
import dev.domain.Vehicule;
import dev.repository.AnnonceCovoitRepo;
import dev.repository.AnnonceRepo;
import dev.repository.CollegueRepo;
import dev.repository.ItineraireRepo;
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

	public void ajouterUneAnnonce(String email, Itineraire itineraire, Vehicule vehicule, LocalDateTime dateTime) {

		Optional<Collegue> collegueConnecte = this.collegueRepo.findByEmail(email);
		collegueConnecte.ifPresent(conducteur -> {
			itiRepo.save(itineraire);
			vehiRepo.save(vehicule);
			annonceRepo.save(new AnnonceCovoit(conducteur, itineraire, vehicule, dateTime));
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
}


