package dev.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.controller.vm.CollegueVM;
import dev.domain.Collegue;
import dev.domain.Role;
import dev.domain.RoleCollegue;
import dev.repository.CollegueRepo;



@Repository
public class CollegueService {
	
	@Autowired
    private CollegueRepo collegueRepo;

	public CollegueService() {

    }
	
	public List<CollegueVM> lister() {
        return collegueRepo.findAll()
                .stream()
                .map(c -> {
                	Collegue col = new Collegue(c.getMatricule(), c.getEmail(), c.getNom(), c.getPrenom(), c.getRoles());
                	return new CollegueVM(col);
                	}
                )
                .collect(Collectors.toList());
    }
	
	public List<Collegue> chercherParNom(String nom) {
        return collegueRepo.findByNom(nom);
    }

    public Collegue chercherParMatricule(String matricule) {
        return collegueRepo.findByMatricule(matricule).orElseThrow(() -> new CollegueNonTrouveException());
    }
    
    public void modifierRoles (String matricule, Role roleChauffeur) {
    	
    	Collegue collegue = chercherParMatricule(matricule);
    	
    	if (!matricule.equals(matricule)) {
            throw new CollegueNonTrouveException();
        }
    	
    	collegue.setRoles(roleChauffeur);
    	collegueRepo.save(collegue);
    	
    }

}
