package dev.service;

import dev.controller.vm.ChauffeurVM;
import dev.domain.Collegue;
import dev.domain.Role;
import dev.domain.RoleCollegue;
import dev.exception.CollegueNonTrouveException;
import dev.repository.CollegueRepo;
import dev.repository.RoleCollegueRepo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class CollegueService {
	
	@Autowired
    private CollegueRepo collegueRepo;
	
	@Autowired
	private RoleCollegueRepo roleCollegueRepo;

	public CollegueService() {

    }
	
	public Collegue chercherParEmail (String email) {
		return collegueRepo.findByEmail(email)
				.orElseThrow(() -> new CollegueNonTrouveException());
	}

	public List<ChauffeurVM> lister() {
        return collegueRepo.findAll()
                .stream()
                .map(c -> {
                	ChauffeurVM col = new ChauffeurVM(c);
                	return col;
                	}
                )
                .filter(collegue -> collegue.getRoles().contains(Role.ROLE_CHAUFFEUR))
                .collect(Collectors.toList());
    }
	
	

    public Collegue chercherParId(Long id) {
        return collegueRepo.findById(id).orElseThrow(() -> new CollegueNonTrouveException());
    }
    
    public void modifierRole (Long id) {
    	Collegue collegue = chercherParId(id);
    	
    	if(!collegue.containsRole(Role.ROLE_CHAUFFEUR)) {
    		List<RoleCollegue> roles = collegue.getRoles();
    		RoleCollegue roleCollegue = new RoleCollegue(collegue,Role.ROLE_CHAUFFEUR);
    		roleCollegueRepo.save(roleCollegue);
    		roles.add(roleCollegue);
    		collegue.setRoles(roles);
    		collegueRepo.save(collegue);	
    	}
    	   	
    }


}
