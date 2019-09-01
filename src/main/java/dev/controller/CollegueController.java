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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Collegue;
import dev.controller.vm.ChauffeurVM;
import dev.controller.vm.CollegueVM;
import dev.domain.Role;
import dev.dto.ChauffeurIdDTO;
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
    
    public List<ChauffeurVM> getAllCollegues (){
        List<ChauffeurVM> collegues = collegueService.lister();
        return collegues;
    }
	
	
	@Secured("ROLE_ADMINISTRATEUR")
	@RequestMapping(
            method = RequestMethod.POST, 
            path = "/chauffeurs")

    public void updateRole(@RequestBody ChauffeurIdDTO id) {
        	collegueService.modifierRole(id.getId());
    }
}
