package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.RoleCollegue;
import dev.domain.Vehicule;

public interface RoleCollegueRepo extends JpaRepository<RoleCollegue, Long>{

}
