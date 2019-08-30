package dev.service;

import dev.controller.vm.CollegueVM;
import dev.domain.Collegue;
import dev.domain.Role;
import dev.exception.CollegueNonTrouveException;
import dev.repository.CollegueRepo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class CollegueService {
	
	@Autowired
    private CollegueRepo collegueRepo;

	public CollegueService() {

    }
	
	public Collegue chercherParEmail (String email) {
		return collegueRepo.findByEmail(email)
				.orElseThrow(() -> new CollegueNonTrouveException());
	}

	public List<CollegueVM> lister() {
        return collegueRepo.findAll()
                .stream()
                .filter(collegue -> collegue.getRoles().contains(Role.ROLE_CHAUFFEUR))
                .map(c -> {
                	CollegueVM col = new CollegueVM(c);
                	return col;
                	}
                )
                .collect(Collectors.toList());
    }
	
	public List<Collegue> chercherParNom(String nom) {
        return collegueRepo.findByNom(nom);
    }

    public Collegue chercherParId(Long id) {
        return collegueRepo.findById(id).orElseThrow(() -> new CollegueNonTrouveException());
    }
    
//    public void modifierRoles (Long id, Role roleChauffeur) {
//    	
//    	Collegue collegue = chercherParId(id);
//    	
//    	if (!collegue.getId().equals(id)) {
//            throw new CollegueNonTrouveException();
//        }
//    	
//    	collegue.setRoles(roleChauffeur);
//    	collegueRepo.save(collegue);
//    	
//    }


}
