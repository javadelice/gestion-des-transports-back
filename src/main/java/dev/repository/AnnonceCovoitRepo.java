package dev.repository;

import dev.domain.AnnonceCovoit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnonceCovoitRepo extends JpaRepository<AnnonceCovoit, Integer> {

	List<AnnonceCovoit> getAllByDateTimeBetween (LocalDateTime start, LocalDateTime end);
	
}

