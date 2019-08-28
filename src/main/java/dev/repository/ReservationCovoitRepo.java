package dev.repository;

import dev.domain.AnnonceCovoit;
import dev.domain.Collegue;
import dev.domain.ReservationCovoit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationCovoitRepo extends JpaRepository<ReservationCovoit, Integer> {

    Optional<List<ReservationCovoit>> getAllByPassagers(Collegue collegue);
    Optional<List<ReservationCovoit>> getAllByAnnonceCovoit (AnnonceCovoit annonce);
   

}
