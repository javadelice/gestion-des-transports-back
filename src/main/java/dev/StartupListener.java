package dev;

import dev.domain.*;
import dev.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * Code de démarrage de l'application.
 * Insertion de jeux de données.
 */
@Component
public class StartupListener {

    private String appVersion;
    private VersionRepo versionRepo;
    private PasswordEncoder passwordEncoder;
    private CollegueRepo collegueRepo;
    private AnnonceCovoitRepo annonceCovoitRepo;
    private ItineraireRepo itineraireRepo;
    private VehiculeRepo vehiculeRepo;
    private ReservationCovoitRepo reservationCovoitRepo;

    public StartupListener(@Value("${app.version}") String appVersion, VersionRepo versionRepo, PasswordEncoder passwordEncoder,
                           CollegueRepo collegueRepo, AnnonceCovoitRepo annonceCovoitRepo,
                           ItineraireRepo itineraireRepo, VehiculeRepo vehiculeRepo,
                           ReservationCovoitRepo reservationCovoitRepo) {
        this.appVersion = appVersion;
        this.versionRepo = versionRepo;
        this.passwordEncoder = passwordEncoder;
        this.collegueRepo = collegueRepo;
        this.annonceCovoitRepo = annonceCovoitRepo;
        this.itineraireRepo = itineraireRepo;
        this.vehiculeRepo = vehiculeRepo;
        this.reservationCovoitRepo = reservationCovoitRepo;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onStart() {
        this.versionRepo.save(new Version(appVersion));

        // Création de deux utilisateurs

        Collegue col1 = new Collegue();
        col1.setNom("Admin");
        col1.setPrenom("DEV");
        col1.setEmail("admin@dev.fr");
        col1.setMotDePasse(passwordEncoder.encode("superpass"));
        col1.setRoles(Arrays.asList(new RoleCollegue(col1, Role.ROLE_ADMINISTRATEUR), new RoleCollegue(col1, Role.ROLE_UTILISATEUR)));
        this.collegueRepo.save(col1);

        Collegue col2 = new Collegue();
        col2.setNom("User");
        col2.setPrenom("DEV");
        col2.setEmail("user@dev.fr");
        col2.setMotDePasse(passwordEncoder.encode("superpass"));
        col2.setRoles(Arrays.asList(new RoleCollegue(col2, Role.ROLE_UTILISATEUR)));
        this.collegueRepo.save(col2);

        //Création d'un jeu de donnée pour une reservation

        Itineraire itineraire = new Itineraire("Montpellier","Nantes","7h",825);
        this.itineraireRepo.save(itineraire);

        Itineraire itineraire1 = new Itineraire("Nantes","Montpellier","7h",825);
        this.itineraireRepo.save(itineraire1);
        Vehicule vehicule = new Vehicule("AB-344-CA","Renault","Clio",3);
        this.vehiculeRepo.save(vehicule);

        //Annonces
        AnnonceCovoit annonceCovoit = new AnnonceCovoit(col2,itineraire,vehicule, LocalDateTime.of(LocalDate.of(2019,9,3), LocalTime.of(8,30)));
        this.annonceCovoitRepo.save(annonceCovoit);

        AnnonceCovoit annonceCovoit1 = new AnnonceCovoit(col2,itineraire1,vehicule,LocalDateTime.of(LocalDate.of(2019,9,14), LocalTime.of(8,30)));
        this.annonceCovoitRepo.save(annonceCovoit1);

        AnnonceCovoit annonceCovoit2 = new AnnonceCovoit(col2,itineraire1,vehicule,LocalDateTime.of(LocalDate.of(2019,8,3), LocalTime.of(8,30)));
        this.annonceCovoitRepo.save(annonceCovoit2);
        //Reservations
        ReservationCovoit reservationCovoit1 = new ReservationCovoit(annonceCovoit,col1);
        this.reservationCovoitRepo.save(reservationCovoit1);

        ReservationCovoit reservationCovoit2 = new ReservationCovoit(annonceCovoit1,col1);
        this.reservationCovoitRepo.save(reservationCovoit2);

        ReservationCovoit reservationCovoit3 = new ReservationCovoit(annonceCovoit2,col1);
        this.reservationCovoitRepo.save(reservationCovoit3);



    }

}
