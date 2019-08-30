package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.AnnonceCovoit;

public interface AnnonceRepo extends JpaRepository<AnnonceCovoit, Integer> {

}
