package dev.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import dev.domain.Collegue;
import dev.controller.vm.CollegueVM;
import dev.domain.Role;
import dev.service.CollegueService;

@CrossOrigin (allowCredentials = "true")
@RestController
@RequestMapping("/admin")
public class CollegueController {

	@Autowired
    private CollegueService collegueService;
	
	
	@Secured("ROLE_ADMINISTRATEUR")
	@RequestMapping(
            method = RequestMethod.GET,
           path = "/chauffeurs"
            )
    
    public List<CollegueVM> getAllCollegues (){
        List<CollegueVM> collegues = collegueService.lister()
        		.stream()
        		.filter(c -> c.getRoles().contains(Role.ROLE_CHAUFFEUR))
        		.collect(Collectors.toList());
        return collegues;
    }
	
	
	@Secured("ROLE_ADMINISTRATEUR")
	@RequestMapping(
            method = RequestMethod.PATCH, 
            path = "/chauffeurs")

    public void updateRole(@PathVariable String matricule) {

		Collegue collegue = collegueService.chercherParMatricule(matricule);
        if (!collegue.getRoles().contains(Role.ROLE_CHAUFFEUR)) {
        	collegueService.modifierRoles(matricule, Role.ROLE_CHAUFFEUR);
        } 

  }
}
