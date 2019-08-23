package dev.repository;

import dev.domain.Collegue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CollegueRepo extends JpaRepository<Collegue, Long> {

    Optional<Collegue> findByEmail(String email);
    
    Optional<Collegue> findByMatricule(String matricule);
    
    List<Collegue> findByNom(String nom);
    
    List<Collegue> findByPrenom(String prenom);
}
