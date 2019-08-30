package dev.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Dispo;
import dev.domain.Vehicule;
import dev.dto.InfosResa;
import dev.dto.ResaVehiculeDTO;
import dev.service.ResaVehiculeService;

@RestController
public class ResaVehiculeController {

    @Autowired
    private ResaVehiculeService resaVehiculeService;

    @Secured("ROLE_UTILISATEUR")
    @RequestMapping(method = RequestMethod.GET, path = "collaborateur/reservations/vehicule")
    public List<ResaVehiculeDTO> reservation() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return resaVehiculeService.getReservertionEnCours(email).stream().map(reservation -> {
            ResaVehiculeDTO resaVehiculeDto = new ResaVehiculeDTO();
            resaVehiculeDto.setIdResaDto(reservation.getIdResV());
            resaVehiculeDto.setDateDeDebut(reservation.getDateDebutResaV());
            resaVehiculeDto.setDateDeFin(reservation.getDateFinResV());
            resaVehiculeDto.setVehiculeSociete(reservation.getVehicule());
            return resaVehiculeDto;
        }).collect(Collectors.toList());
    }

    @Secured("ROLE_UTILISATEUR")
    @RequestMapping(method = RequestMethod.GET, path = "collaborateur/historique")
    public List<ResaVehiculeDTO> historique() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return resaVehiculeService.getHistorique(email).stream().map(reservation -> {
            ResaVehiculeDTO resaVehiculeDto = new ResaVehiculeDTO();
            resaVehiculeDto.setIdResaDto(reservation.getIdResV());
            resaVehiculeDto.setDateDeDebut(reservation.getDateDebutResaV());
            resaVehiculeDto.setDateDeFin(reservation.getDateFinResV());
            resaVehiculeDto.setVehiculeSociete(reservation.getVehicule());
            return resaVehiculeDto;
        }).collect(Collectors.toList());
    }

    @Secured("ROLE_UTILISATEUR")
    @RequestMapping(method = RequestMethod.GET, path = "collaborateur/reservations/vehicule/creer")
    public List<Vehicule> getVehiculeSociete(@RequestParam String dateDepart,
                                             @RequestParam String heureDepart,
                                             @RequestParam String minuteDepart,
                                             @RequestParam(required = false) String dateRetour,
                                             @RequestParam(required = false) String heureRetour,
                                             @RequestParam(required = false) String minuteRetour){
        LocalDate dateDeDepart = LocalDate.parse(dateDepart, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String infoHoraireDepart = heureDepart + ":" + minuteDepart;
        LocalTime horaireDeDepart = LocalTime.parse(infoHoraireDepart, DateTimeFormatter.ofPattern("HH:mm"));
        LocalDateTime dateTimeDepart = LocalDateTime.of(dateDeDepart, horaireDeDepart);


        List<Vehicule> vehiculeReservedList = new ArrayList<>();
        List<Vehicule> vehiculeList = resaVehiculeService.getListVehiculeSociete();

        if(dateRetour.equals("undefined") && heureRetour.equals("undefined") && minuteRetour.equals("undefined")){
            vehiculeReservedList.addAll(resaVehiculeService.getListVehiculeReserved(dateTimeDepart));
        }
        else{
            LocalDate dateDeRetour = LocalDate.parse(dateRetour, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String infoHoraireRetour = heureRetour + ":" + minuteRetour;
            LocalTime horaireDeRetour = LocalTime.parse(infoHoraireRetour, DateTimeFormatter.ofPattern("HH:mm"));
            LocalDateTime dateTimeRetour = LocalDateTime.of(dateDeRetour, horaireDeRetour);

            vehiculeReservedList.addAll(resaVehiculeService.getListVehiculeReserved(dateTimeDepart,dateTimeRetour));
        }

        return vehiculeList.stream()
                .map(vehicule -> {
                    if(vehiculeReservedList.contains(vehicule)){
                        vehicule.setIndisponible(Dispo.Indisponible);
                    }
                    else vehicule.setIndisponible(Dispo.Disponible);
                    return vehicule;
                }).collect(Collectors.toList());
    }

    @Secured("ROLE_UTILISATEUR")
    @RequestMapping(method = RequestMethod.POST, path = "collaborateur/reservations/vehicule/creer")
    public void ajouterReservation(@RequestBody InfosResa infoResa) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        
        LocalDate dateDeDepart = LocalDate.parse(infoResa.getDateDepart(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String infoHoraireDepart = infoResa.getHeureDepart() + ":" + infoResa.getMinuteDepart();
        LocalTime horaireDeDepart = LocalTime.parse(infoHoraireDepart, DateTimeFormatter.ofPattern("HH:mm"));
        LocalDateTime dateTimeDepart = LocalDateTime.of(dateDeDepart, horaireDeDepart);
        
        LocalDate dateDeRetour = LocalDate.parse(infoResa.getDateRetour(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String infoHoraireRetour = infoResa.getHeureRetour() + ":" + infoResa.getMinuteRetour();
        LocalTime horaireDeRetour = LocalTime.parse(infoHoraireRetour, DateTimeFormatter.ofPattern("HH:mm"));
        LocalDateTime dateTimeRetour = LocalDateTime.of(dateDeRetour, horaireDeRetour);
        
        resaVehiculeService.verifierDate(dateTimeDepart, dateTimeRetour, infoResa.getVehiculeSociete(), email);
    }
}
