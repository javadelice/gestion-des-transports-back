package dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.AnnonceCovoit;

public interface AnnonceCovoitRepo extends JpaRepository<AnnonceCovoit, Integer> {
	
	Optional <AnnonceCovoit> findById (AnnonceCovoit annonceCo);
	
}

