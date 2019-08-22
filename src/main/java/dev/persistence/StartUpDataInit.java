package dev.persistence;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.domain.ResaVehicule;
import dev.repository.ResaVehiculeRepository;

@Component
public class StartUpDataInit {

    @Autowired
    private ResaVehiculeRepository resaVehiculeRepo;

    public void init() {
        resaVehiculeRepo.save(new ResaVehicule(LocalDateTime.now(), LocalDateTime.of(2019, 8, 25, 12, 30), "AA-2520-BB",
                "Renault", 2001));
        resaVehiculeRepo.save(new ResaVehicule(LocalDateTime.now(), LocalDateTime.of(2019, 8, 31, 12, 30), "AC-2520-DE",
                "Peugoet", 2009));
        resaVehiculeRepo.save(new ResaVehicule(LocalDateTime.of(2017, 12, 01, 11, 00), LocalDateTime.of(2017, 12, 01, 15, 00), "AC-2520-DE",
                "Peugoet", 2009));
    }
}
