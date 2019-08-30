package dev.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.AnnonceCovoit;

public interface AnnonceCovoitRepo extends JpaRepository<AnnonceCovoit, Integer> {

	List<AnnonceCovoit> getAllByDateTimeBetween (LocalDateTime start, LocalDateTime end);
	AnnonceCovoit getAnnonceById (int id);


}

