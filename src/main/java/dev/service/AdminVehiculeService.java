package dev.service;

import dev.domain.Vehicule;
import dev.repository.VehiculeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminVehiculeService {

    @Autowired
    VehiculeRepo vehiculeRepo;

    public List<Vehicule> getVehiculesSociete(){
        return vehiculeRepo.getVehiculesByEstSociete(true).orElse(new ArrayList<>());
    }

    public void checkVehicule(Vehicule vehicule){
        this.addVehiculeSociete(vehicule);
    }

    public void addVehiculeSociete(Vehicule vehicule){
        this.vehiculeRepo.save(vehicule);
    }
}
