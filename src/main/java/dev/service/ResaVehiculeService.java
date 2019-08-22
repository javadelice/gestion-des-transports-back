package dev.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.domain.ResaVehicule;
import dev.repository.ResaVehiculeRepository;

@Service
public class ResaVehiculeService {

    @Autowired
    private ResaVehiculeRepository resaVehiculeRepo;
    
    
    public List<ResaVehicule> getResaV() {
        
        return resaVehiculeRepo.findAll()
                .stream()
                .filter(resa -> resa.getDateDebutResaV().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }
}
