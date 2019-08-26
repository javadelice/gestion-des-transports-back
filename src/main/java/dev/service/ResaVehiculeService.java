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
import dev.dto.ResaVehiculeDto;
import dev.repository.CollegueRepo;
import dev.repository.ResaVehiculeRepository;

@Service
public class ResaVehiculeService {

    @Autowired
    private CollegueRepo collegueRepos;

    @Autowired
    private ResaVehiculeRepository resaVehiculeRepo;

    public List<ResaVehicule> getReserveationEnCours(String email) {
        Optional<Collegue> collegue = this.collegueRepos.findByEmail(email);

        List<ResaVehicule> reservationVehicule = new ArrayList<>();
        collegue.ifPresent(reservation -> {
            for (ResaVehicule resa : reservation) {
                Optional<ResaVehicule> resaVehicule = this.resaVehiculeRepo.findById(resa.getIdResV())
                        .filter(resaV -> resaV.getDateDebutResaV().isAfter(LocalDateTime.now()));
                reservationVehicule.add(resa);
            }
        });
        return reservationVehicule;

    }
}
