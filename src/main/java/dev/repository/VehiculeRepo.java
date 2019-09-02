package dev.repository;

import dev.domain.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehiculeRepo extends JpaRepository<Vehicule, Integer> {

    public Optional<List<Vehicule>> getVehiculesByEstSociete(boolean estSociete);
    public Optional<Vehicule> getVehiculeByImmatriculation (String immatriculation);
}
