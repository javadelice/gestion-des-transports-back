package dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.InfosVille;

public interface InfosVilleRepo extends JpaRepository <InfosVille, Long> {
	
	public Optional <InfosVille> getInfosVilleByVille (String ville);

}
  