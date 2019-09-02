package dev.repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Collegue;
import dev.domain.ResaVehicule;
import dev.domain.Vehicule;

public interface ResaVehiculeRepository extends JpaRepository<ResaVehicule, Long>{

    public Optional<List<ResaVehicule>> getAllByPassager(Collegue collegue);
    public Optional<List<ResaVehicule>> getAllByVehicule(Vehicule vehicule);
    

    public Optional<List<ResaVehicule>> getResaVehiculesByDateDebutResaVIsBeforeAndDateFinResVIsAfter(LocalDateTime dateTimeDateDebut,LocalDateTime DateTimeFin);

    public Optional<List<ResaVehicule>> getResaVehiculesByDateDebutResaVIsAfterAndAndDateFinResVIsBefore(LocalDateTime dateTimeDebut,LocalDateTime dateTimeFin);
}
