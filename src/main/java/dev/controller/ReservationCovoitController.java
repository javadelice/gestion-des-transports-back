package dev.controller;

import dev.domain.AnnonceCovoit;
import dev.domain.Collegue;
import dev.dto.AnnonceCovoitDTO;
import dev.dto.CollegueDTO;
import dev.dto.DateVoyage;
import dev.service.CollegueService;
import dev.service.CovoitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReservationCovoitController {

    @Autowired
    CovoitService covoitService;
    @Autowired
    CollegueService collegueService;
   

    @Secured("ROLE_UTILISATEUR")
    @RequestMapping(method = RequestMethod.GET,
    path = "/collaborateur/reservations")
    public List<AnnonceCovoitDTO> getListResa(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<AnnonceCovoit> annonceCovoitList = this.covoitService.getLesAnnonceReservedBy(email);
        return annonceCovoitList.stream()
                .map(annonce->{
                    AnnonceCovoitDTO annonceCovoitDTO = new AnnonceCovoitDTO();
                    annonceCovoitDTO.setId(annonce.getId());
                    annonceCovoitDTO.setCollegue(new CollegueDTO(annonce.getConducteur()));
                    annonceCovoitDTO.setItineraire(annonce.getItineraire());
                    annonceCovoitDTO.setVehicule(annonce.getVehicule());
                    annonceCovoitDTO.setDateTime(annonce.getDateTime());
                    return annonceCovoitDTO;
                })
                .collect(Collectors.toList());
    }
    
    @Secured("ROLE_UTILISATEUR")
    @RequestMapping(method = RequestMethod.GET,
    path = "/collaborateur/reservations/covoit/creer")
    
    public List<AnnonceCovoitDTO> getListResaCovoit(@RequestParam DateVoyage date, @RequestParam String lieuDepart, @RequestParam String lieuArrivee ){
    	LocalDate selectedDate = LocalDate.parse(date.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    	LocalTime startTime = LocalTime.of(0, 0);
    	LocalTime endTime = LocalTime.of(23, 59);
    	LocalDateTime start = LocalDateTime.of(selectedDate, startTime);
    	LocalDateTime end = LocalDateTime.of(selectedDate, endTime);
        List<AnnonceCovoit> annonceCovoitList = this.covoitService.selectByDate(start, end);	
        return annonceCovoitList.stream()
        		.filter(annonce -> annonce.getItineraire().getAdresseDepart().equals(lieuDepart))
        		.filter(annonce -> annonce.getItineraire().getAdresseDest().equals(lieuArrivee))
                .map(annonce->{
                	int nbPlaceSLibres = covoitService.getNbPlacesLibres(annonce);
                    AnnonceCovoitDTO annonceCovoitDTO = new AnnonceCovoitDTO();
                    annonceCovoitDTO.setId(annonce.getId());
                    annonceCovoitDTO.setCollegue(new CollegueDTO(annonce.getConducteur()));
                    annonceCovoitDTO.setItineraire(annonce.getItineraire());
                    annonceCovoitDTO.setVehicule(annonce.getVehicule());
                    annonceCovoitDTO.setDateTime(annonce.getDateTime());
                    annonceCovoitDTO.setNbPlacesLibres(nbPlaceSLibres);
                    return annonceCovoitDTO;
                })
                .collect(Collectors.toList());
    }
    
    @Secured("ROLE_UTILISATEUR")
    @RequestMapping(method = RequestMethod.POST,
    		path = "/collaborateur/reservations/covoit/creer")
    @PostMapping(value="/collaborateur/reservations")
    
    public void newReservationCovoit (@RequestBody AnnonceCovoitDTO annonce) {
    	String email = SecurityContextHolder.getContext().getAuthentication().getName();
    	Collegue passager = collegueService.chercherParEmail(email);
    	
    	AnnonceCovoit annonceChoisie = covoitService.getResaCovoit(annonce.getId());    	
    	covoitService.addBooking(annonceChoisie, passager);
    	
    }
    

    @Secured("ROLE_UTILISATEUR")
    @RequestMapping(method = RequestMethod.GET,
            path = "/collaborateur/reservations_old")
    public List<AnnonceCovoitDTO> getListResaOld(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<AnnonceCovoit> annonceCovoitList = this.covoitService.getLesAnnonceOldReservedBy(email);
        return annonceCovoitList.stream()
                .map(annonce->{
                    AnnonceCovoitDTO annonceCovoitDTO = new AnnonceCovoitDTO();
                    annonceCovoitDTO.setId(annonce.getId());
                    annonceCovoitDTO.setCollegue(new CollegueDTO(annonce.getConducteur()));
                    annonceCovoitDTO.setItineraire(annonce.getItineraire());
                    annonceCovoitDTO.setVehicule(annonce.getVehicule());
                    annonceCovoitDTO.setDateTime(annonce.getDateTime());
                    return annonceCovoitDTO;
                })
                .collect(Collectors.toList());
    }
}
