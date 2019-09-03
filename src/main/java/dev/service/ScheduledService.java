package dev.service;

import dev.domain.AnnonceCovoit;
import dev.domain.ReservationCovoit;
import dev.domain.Statut;
import dev.repository.AnnonceCovoitRepo;
import dev.repository.ReservationCovoitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduledService {


    @Autowired
    ReservationCovoitRepo reservationCovoitRepo;

    @Autowired
    AnnonceCovoitRepo annonceCovoitRepo;


    @Scheduled(cron = "0 * * * * ?")
    public void setStatutTerminer(){
        // on récupere toute les reservations qui ont un statut STATUT_ENCOURS et on filtre la date de départ inférieur
        // à la date d'aujourd'hui
        List<ReservationCovoit> reservationCovoitList = reservationCovoitRepo
                .getReservationCovoitsByStatut(Statut.STATUT_ENCOURS)
                .stream()
                .filter(reservationCovoit -> reservationCovoit.getAnnonceCovoit().getDateTime().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());
        if(reservationCovoitList.size() > 0){
            for(ReservationCovoit resa : reservationCovoitList){
                AnnonceCovoit annonceCovoit = resa.getAnnonceCovoit();
                annonceCovoit.setStatut(Statut.STATUT_TERMINEE);
                annonceCovoitRepo.save(annonceCovoit);
                resa.setStatut(Statut.STATUT_TERMINEE);
                reservationCovoitRepo.save(resa);
            }
        }


    }

}
