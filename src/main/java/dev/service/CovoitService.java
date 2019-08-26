package dev.service;

import dev.domain.AnnonceCovoit;
import dev.domain.Collegue;
import dev.domain.ReservationCovoit;
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
    
    public List<AnnonceCovoit> getAllAnoncesCovoit () {
    	return annonceCovoitRepo.findAll()
    			.stream()
    			.map(c -> new AnnonceCovoit(c.getDateTime(), c.getItineraire().getAdresseDepart(), c.getItineraire().getAdresseDest(), c.getVehicule().getModele(), c.getConducteur().getNom(), c.getConducteur().getPrenom(), c.getVehicule().getNbPlaceDispo()))
    			.collect(Collectors.toList());

    	
    }
    
    public List<AnnonceCovoit> selectByDate (LocalDateTime start, LocalDateTime end) {
    	return annonceCovoitRepo.getAllByDateTimeBetween(start, end);
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
        List<AnnonceCovoit> annonceCovoitList = new ArrayList<>();
        if(reservationCovoitList.size() > 0){
            for(ReservationCovoit resa : reservationCovoitList){
                Optional<AnnonceCovoit> annonceCovoitOpt = this.annonceCovoitRepo.findById(resa.getId()).filter(annonceCovoit -> annonceCovoit.getDateTime().isAfter(LocalDateTime.now()));
                annonceCovoitOpt.ifPresent(annonceCovoit-> annonceCovoitList.add(annonceCovoit));
            }

        }
        return annonceCovoitList;
    }
    public List<AnnonceCovoit> getLesAnnonceOldReservedBy(String email){
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
        List<AnnonceCovoit> annonceCovoitList = new ArrayList<>();
        if(reservationCovoitList.size() > 0){
            for(ReservationCovoit resa : reservationCovoitList){
                Optional<AnnonceCovoit> annonceCovoitOpt = this.annonceCovoitRepo.findById(resa.getId()).filter(annonceCovoit -> annonceCovoit.getDateTime().isBefore(LocalDateTime.now()));
                annonceCovoitOpt.ifPresent(annonceCovoit-> annonceCovoitList.add(annonceCovoit));
            }

        }
        return annonceCovoitList;
    }

}
