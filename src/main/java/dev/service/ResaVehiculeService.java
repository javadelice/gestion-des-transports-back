package dev.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.domain.Collegue;
import dev.domain.ResaVehicule;
import dev.repository.CollegueRepo;
import dev.repository.ResaVehiculeRepository;

@Service
public class ResaVehiculeService {

    @Autowired
    private CollegueRepo collegueRepos;

    @Autowired
    private ResaVehiculeRepository resaVehiculeRepo;

    public List<ResaVehicule> getReserveationEnCours(String email) {
        Optional<Collegue> collegueOpt = this.collegueRepos.findByEmail(email);

        List<ResaVehicule> reservationVehicule = new ArrayList<>();
        collegueOpt.ifPresent(collegue -> {
            Optional<List<ResaVehicule>> reservationOpt = this.resaVehiculeRepo.getAllByPassager(collegue);
            reservationOpt.ifPresent(reservations -> {
                for (ResaVehicule resa : reservations) {
                    reservationVehicule.add(resa);
                }
            });
        });

        return reservationVehicule.stream().filter(resa -> resa.getDateDebutResaV().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }
}
