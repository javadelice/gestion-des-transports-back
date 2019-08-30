package dev.controller;

import dev.domain.AnnonceCovoit;
import dev.domain.Collegue;
import dev.domain.ReservationCovoit;
import dev.domain.Statut;
import dev.dto.AnnonceCovoitDTO;
import dev.dto.CollegueDTO;
import dev.dto.DateVoyage;
import dev.dto.ReservationCovoitDTO;
import dev.exception.ReservationNonTrouveException;
import dev.service.CollegueService;
import dev.service.CovoitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
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
    public List<ReservationCovoitDTO> getListResa(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();   
        return this.covoitService.getLesReservationsBy(email);
    }
    
    @Secured("ROLE_UTILISATEUR")
    @RequestMapping(method = RequestMethod.GET,
            path = "/collaborateur/reservations_old")
    public List<ReservationCovoitDTO> getListResaOld(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
                 return this.covoitService.getLesReservationsOldBy(email);

    }
    
    @Secured("ROLE_UTILISATEUR")
    @RequestMapping(method = RequestMethod.GET,
    path = "/collaborateur/reservations/covoit/creer")
    
    public List<AnnonceCovoitDTO> getListResaCovoit(@RequestParam String lieuDepart, @RequestParam String lieuArrivee, @RequestParam DateVoyage date ){
    	LocalDate selectedDate = LocalDate.parse(date.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    	LocalTime startTime = LocalTime.of(0, 0);
    	LocalTime endTime = LocalTime.of(23, 59);
    	LocalDateTime start = LocalDateTime.of(selectedDate, startTime);
    	LocalDateTime end = LocalDateTime.of(selectedDate, endTime);
        List<AnnonceCovoit> annonceCovoitList = this.covoitService.selectByDate(start, end);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<AnnonceCovoit> annonceCovoitListReserved = this.covoitService.getLesAnnonceReservedBy(email);
                
        return annonceCovoitList.stream()
        		.filter(annonce -> annonce.getItineraire().getAdresseDepart().equals(lieuDepart))
        		.filter(annonce -> annonce.getItineraire().getAdresseDest().equals(lieuArrivee))
        		.filter(annonce -> !annonceCovoitListReserved.contains(annonce))
        		.filter(annonce -> annonce.getStatut().equals(Statut.STATUT_ENCOURS))
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
    	
    	AnnonceCovoit annonceChoisie = covoitService.getAnnonceCovoit(annonce.getId());    	
    	covoitService.addBooking(annonceChoisie, passager);    	
    }
    
    
    
    @Secured("ROLE_UTILISATEUR")
    @RequestMapping (method = RequestMethod.PATCH,
    		path = "/collaborateur/reservations")
    public void annulerResaCovoit (@RequestBody ReservationCovoit resa) throws ReservationNonTrouveException, MessagingException {
    	String email = SecurityContextHolder.getContext().getAuthentication().getName();
    	this.covoitService.cancelBooking(email, resa);

    	
    }
    

   
}
