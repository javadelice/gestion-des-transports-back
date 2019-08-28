package dev.service;

import dev.domain.AnnonceCovoit;
import dev.domain.Collegue;
import dev.domain.ReservationCovoit;
import dev.domain.Statut;
import dev.dto.AnnonceCovoitDTO;
import dev.dto.CollegueDTO;
import dev.dto.ReservationCovoitDTO;
import dev.exceptions.AnnonceNonTrouveException;
import dev.exceptions.VoyageCompletException;
import dev.repository.AnnonceCovoitRepo;
import dev.repository.CollegueRepo;
import dev.repository.ReservationCovoitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CovoitService {
    @Autowired
    AnnonceCovoitRepo annonceCovoitRepo;
    @Autowired
    CollegueRepo collegueRepo;
    @Autowired
    ReservationCovoitRepo reservationCovoitRepo;
    
    
    public void addBooking (AnnonceCovoit annonce, Collegue passager) {    	
    	int nbPlaceLibre = this.getNbPlacesLibres(annonce);    	
    	ReservationCovoit resa = new ReservationCovoit(annonce, passager);
    	resa.setStatutResa(Statut.STATUT_ENCOURS);
    	
    	if(nbPlaceLibre > 0) {  		
    		annonceCovoitRepo.save(annonce);
    		collegueRepo.save(passager);
    		reservationCovoitRepo.save(resa);
    	}
 
    	else {
    		throw new VoyageCompletException("Plus de disponibilités pour ce trajet");
    	}
    }
    
    public List<AnnonceCovoit> getLesAnnonceReservedBy(String email){
    	
    	Optional<Collegue> colOpt = this.collegueRepo.findByEmail(email);
        List<ReservationCovoit> reservationCovoitList = new ArrayList<>();
        colOpt.ifPresent(col->{
            Optional<List<ReservationCovoit>> optionalReservationCovoitList = this.reservationCovoitRepo.getAllByPassagers(col);
            optionalReservationCovoitList.ifPresent(mesResa->{
                for(ReservationCovoit resa : mesResa){
                    reservationCovoitList.add(resa);
                }
            });
        });
        return reservationCovoitList.stream()
        		.map(resa -> resa.getAnnonceCovoit())
        		.collect(Collectors.toList());
    }
    
    
    public int getNbPlacesLibres (AnnonceCovoit annonce) {
    	Optional<List<ReservationCovoit>> optionalReservationCovoitList = this.reservationCovoitRepo.getAllByAnnonceCovoit(annonce);
    	List<ReservationCovoit> resaCovoit = new ArrayList<>();
    	optionalReservationCovoitList.ifPresent(listResa -> {
    		for(ReservationCovoit resa : listResa) {
    			resaCovoit.add(resa);
    		}
    	});
    	int nbResa = resaCovoit.size();
    	int nbPlaceLibre = annonce.getVehicule().getNbPlaceDispo()-nbResa;
    	return nbPlaceLibre;
    }
    
    public AnnonceCovoit getResaCovoit (int id) {
    	return annonceCovoitRepo.findById(id)
    			.orElseThrow(() -> new AnnonceNonTrouveException ());
    }
    
    
    public List<AnnonceCovoit> selectByDate (LocalDateTime start, LocalDateTime end) {
    	return annonceCovoitRepo.getAllByDateTimeBetween(start, end);
    }
    

    public List<ReservationCovoitDTO> getLesReservationsBy(String email){
        Optional<Collegue> colOpt = this.collegueRepo.findByEmail(email);
        List<ReservationCovoit> reservationCovoitList = new ArrayList<>();
        colOpt.ifPresent(col->{
            Optional<List<ReservationCovoit>> optionalReservationCovoitList = this.reservationCovoitRepo.getAllByPassagers(col);
            optionalReservationCovoitList.ifPresent(mesResa->{
                for(ReservationCovoit resa : mesResa){
                    reservationCovoitList.add(resa);
                }
            });
        });
        List<ReservationCovoitDTO> listResaCovoit = new ArrayList<>();
        for(ReservationCovoit resa : reservationCovoitList) {
        	ReservationCovoitDTO resaCovoit = new ReservationCovoitDTO(new AnnonceCovoitDTO(resa.getAnnonceCovoit()), resa.getStatutResa());
        	listResaCovoit.add(resaCovoit);
        }
        return listResaCovoit.stream()
        		.filter(resa-> resa.getAnnonce().getDateTime().isAfter(LocalDateTime.now()))
        		.collect(Collectors.toList());
    }
    
    
    public List<ReservationCovoitDTO> getLesReservationsOldBy(String email){
        Optional<Collegue> colOpt = this.collegueRepo.findByEmail(email);
        List<ReservationCovoit> reservationCovoitList = new ArrayList<>();
        colOpt.ifPresent(col->{
            Optional<List<ReservationCovoit>> optionalReservationCovoitList = this.reservationCovoitRepo.getAllByPassagers(col);
            optionalReservationCovoitList.ifPresent(mesResa->{
                for(ReservationCovoit resa : mesResa){
                    reservationCovoitList.add(resa);
                }
            });
        });
        List<ReservationCovoitDTO> listResaCovoit = new ArrayList<>();
        for(ReservationCovoit resa : reservationCovoitList) {
        	ReservationCovoitDTO resaCovoit = new ReservationCovoitDTO(new AnnonceCovoitDTO(resa.getAnnonceCovoit()), resa.getStatutResa());
        	listResaCovoit.add(resaCovoit);
        }
        return listResaCovoit.stream()
        		.filter(resa-> resa.getAnnonce().getDateTime().isBefore(LocalDateTime.now()))
        		.collect(Collectors.toList());
    }

}
