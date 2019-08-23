package dev.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.domain.Collegue;
import dev.repository.CollegueRepo;



@Repository
public class CollegueService {
	
	@Autowired
    private CollegueRepo collegueRepo;

	public CollegueService() {

    }
	
	public List<Collegue> lister() {
        return collegueRepo.findAll()
                .stream()
                .map(c -> new Collegue(c.getMatricule(), c.getNom(), c.getPrenom(), c.getRoles()))
                .collect(Collectors.toList());
    }
	
	public List<Collegue> chercherParNom(String nom) {
        return collegueRepo.findByNom(nom);
    }

    public Collegue chercherParMatricule(String matricule) {
        return collegueRepo.findByMatricule(matricule).orElseThrow(() -> new CollegueNonTrouveException());
    }
}
