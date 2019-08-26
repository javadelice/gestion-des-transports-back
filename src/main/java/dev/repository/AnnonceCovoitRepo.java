package dev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.AnnonceCovoit;
import dev.domain.Collegue;

public interface AnnonceCovoitRepo extends JpaRepository<AnnonceCovoit, Integer> {
	
	Optional<List<AnnonceCovoit>> findAllByConducteur (Collegue collegue); 

}

