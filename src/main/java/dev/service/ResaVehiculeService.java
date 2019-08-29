package dev.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.repository.VehiculeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.domain.Collegue;
import dev.domain.ResaVehicule;
import dev.domain.Vehicule;
import dev.exception.DateReservationVehiculeInvalide;
import dev.repository.CollegueRepo;
import dev.repository.ResaVehiculeRepository;

@Service
public class ResaVehiculeService {

    @Autowired
    private CollegueRepo collegueRepos;

    @Autowired
    private ResaVehiculeRepository resaVehiculeRepo;

    @Autowired
    private VehiculeRepo vehiculeRepo;

    public List<ResaVehicule> getReservertionEnCours(String email) {
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

        return reservationVehicule.stream().filter(resa -> resa.getDateFinResV().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    public List<ResaVehicule> getHistorique(String email) {
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

        return reservationVehicule.stream().filter(resa -> resa.getDateFinResV().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    public void verifierDate(LocalDateTime dateTimeDepart, LocalDateTime dateTimeRetour, Vehicule vehiculeSociete,
            String email) {
        Map<String, String> erreurs = new HashMap<>();

        if (dateTimeDepart != null && dateTimeDepart.isBefore(LocalDateTime.now())) {
            erreurs.put("dateDebut", "La date de réservation ne peut être antérieure à aujourd'hui");
        }
        if (dateTimeRetour != null && dateTimeRetour.isBefore(LocalDateTime.now())) {
            erreurs.put("dateDebut", "La date de retour ne peut être antérieure à aujourd'hui");
        }
        if (!erreurs.isEmpty()) {
            throw new DateReservationVehiculeInvalide(erreurs);
        }
        this.ajouterReservation(dateTimeDepart, dateTimeRetour, vehiculeSociete, email);
    }

    public void ajouterReservation(LocalDateTime dateTimeDepart, LocalDateTime dateTimeRetour, Vehicule vehiculeSociete,
            String email) {
        Optional<Collegue> collegueOpt = this.collegueRepos.findByEmail(email);
        collegueOpt.ifPresent(collegue -> {
            ResaVehicule uneResa = new ResaVehicule();
            uneResa.setDateDebutResaV(dateTimeDepart);
            uneResa.setDateFinResV(dateTimeRetour);
            uneResa.setVehicule(vehiculeSociete);
            uneResa.setPassager(collegue);
            resaVehiculeRepo.save(uneResa);
        });
    }

    public List<Vehicule> getListVehiculeSociete(){
        List<Vehicule> vehiculeList= this.vehiculeRepo.getVehiculesByEstSociete(true).get();
        return vehiculeList;
    }

    public List<ResaVehicule> getResaVehiculeBetween(LocalDateTime dateDebut){
        return resaVehiculeRepo.getResaVehiculesByDateDebutResaVIsBeforeAndDateFinResVIsAfter(dateDebut,dateDebut)
                .orElse(new ArrayList<ResaVehicule>());
    }

    public List<Vehicule> getListVehiculeReserved(LocalDateTime dateDebut){
        List<ResaVehicule> resaVehiculeList = getResaVehiculeBetween(dateDebut);
        List<Vehicule> vehiculeList = new ArrayList<>();
        if(resaVehiculeList.size() > 0){
            for (ResaVehicule resa : resaVehiculeList){
                vehiculeList.add(resa.getVehicule());
            }
        }
        return vehiculeList;
    }

}
