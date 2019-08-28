package dev.repository;

import dev.domain.AnnonceCovoit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AnnonceCovoitRepo extends JpaRepository<AnnonceCovoit, Integer> {

	List<AnnonceCovoit> getAllByDateTimeBetween (LocalDateTime start, LocalDateTime end);


}

