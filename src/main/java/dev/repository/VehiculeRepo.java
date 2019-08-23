package dev.repository;

import dev.domain.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculeRepo extends JpaRepository<Vehicule, Integer> {


}
