package dev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Vehicule;

public interface VehiculeRepo extends JpaRepository<Vehicule, Integer> {

    public Optional<List<Vehicule>> getVehiculesByEstSociete(boolean estSociete);
    public Optional<Vehicule> getVehiculeByImmatriculation (String immatriculation);
}
