package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.dto.ResaVehiculeDto;
import dev.service.ResaVehiculeService;

@RestController
public class ResaVehiculeController {

    @Autowired
    private ResaVehiculeService resaVehiculeService;
    
    @RequestMapping(method = RequestMethod.GET, path="/reservations")
    public List<ResaVehiculeDto> reservation(){
        return resaVehiculeService.getReserveationEnCours();
    }
}
