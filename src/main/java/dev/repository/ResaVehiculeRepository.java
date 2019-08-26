package dev.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Collegue;
import dev.domain.ResaVehicule;

public interface ResaVehiculeRepository extends JpaRepository<ResaVehicule, Long>{

    public Optional<List<ResaVehicule>> getAllByPassager(Collegue collegue);
}
