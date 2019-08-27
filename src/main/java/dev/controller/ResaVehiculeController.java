package dev.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import dev.dto.ResaVehiculeDTO;
import dev.service.ResaVehiculeService;

@RestController
public class ResaVehiculeController {

    @Autowired
    private ResaVehiculeService resaVehiculeService;

    @Secured("ROLE_UTILISATEUR")
    @RequestMapping(method = RequestMethod.GET, path = "/reservations")
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
    @RequestMapping(method = RequestMethod.GET, path = "/historique")
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
}
