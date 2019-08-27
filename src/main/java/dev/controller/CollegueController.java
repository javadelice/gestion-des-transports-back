package dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.service.CollegueService;

@CrossOrigin (allowCredentials = "true")
@RestController
@RequestMapping("/admin")
public class CollegueController {

	@Autowired
    private CollegueService collegueService;
	
	
	
	

}
