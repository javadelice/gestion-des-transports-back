package dev.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.CollegueVM;

import dev.service.CollegueService;

@CrossOrigin (allowCredentials = "true")
@RestController
@RequestMapping("/admin")
public class CollegueController {

	@Autowired
    private CollegueService collegueService;
	
//	@RequestMapping(
//            method = RequestMethod.GET
//            )
//
//    public List<Long> recupId(@RequestParam String nom) {
//
//        List<Long> id = collegueService.chercherParNom(nom)
//                .stream()
//                .map(c -> c.getId())
//                .collect(Collectors.toList());
//
//        return id;
//
//    }
	
	@Secured("ROLE_ADMINISTRATEUR")
	@RequestMapping(
            method = RequestMethod.GET,
            path = "/chauffeurs"
            )
    
    public List<CollegueVM> getAllCollegues (){
        List<CollegueVM> collegues = collegueService.lister();
        return collegues;
    }
}
