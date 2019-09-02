package dev.controller;

import dev.domain.Vehicule;
import dev.service.AdminVehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GestionVehiculeController {

    @Autowired
    AdminVehiculeService adminVehiculeService;

    @Secured("ROLE_ADMINISTRATEUR")
    @RequestMapping(method = RequestMethod.GET,path = "admin/vehicules")
    public List<Vehicule> getLesVehiculeSociete(){
    return adminVehiculeService.getVehiculesSociete();
    }

    @Secured("ROLE_ADMINISTRATEUR")
    @RequestMapping(method = RequestMethod.POST, path = "admin/vehicules")
    public ResponseEntity<Object> addVehiculeSociete(@RequestBody Vehicule vehicule){
        adminVehiculeService.checkVehicule(vehicule);
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }
}
