package dev;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import dev.domain.*;
import dev.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.domain.Collegue;
import dev.domain.ResaVehicule;
import dev.domain.Role;
import dev.domain.RoleCollegue;
import dev.domain.Version;
import dev.repository.CollegueRepo;
import dev.repository.ResaVehiculeRepository;
import dev.repository.VersionRepo;

/**
 * Code de démarrage de l'application. Insertion de jeux de données.
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

    @Autowired
    private ResaVehiculeRepository resaVehiculeRepo;

    public StartupListener(@Value("${app.version}") String appVersion, VersionRepo versionRepo,
            PasswordEncoder passwordEncoder, CollegueRepo collegueRepo, AnnonceCovoitRepo annonceCovoitRepo,
            ItineraireRepo itineraireRepo, VehiculeRepo vehiculeRepo, ReservationCovoitRepo reservationCovoitRepo) {
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
        col1.setRoles(Arrays.asList(new RoleCollegue(col1, Role.ROLE_ADMINISTRATEUR),
                new RoleCollegue(col1, Role.ROLE_UTILISATEUR)));
        this.collegueRepo.save(col1);

        Collegue col2 = new Collegue();
        col2.setNom("User");
        col2.setPrenom("DEV");
        col2.setEmail("user@dev.fr");
        col2.setMotDePasse(passwordEncoder.encode("superpass"));
        col2.setRoles(Arrays.asList(new RoleCollegue(col2, Role.ROLE_UTILISATEUR)));
        this.collegueRepo.save(col2);

        // Création véhicule de société
        Vehicule vehiculeSo = new Vehicule("AC-985-CA", "Peugeot", 2008, 3, true,
                "https://images.caradisiac.com/images/6/8/4/3/176843/S1-nouvelle-peugeot-208-prix-a-partir-de-15-500-eur-593600.jpg",
                Categorie.SUV, Dispo.Disponible);
        this.vehiculeRepo.save(vehiculeSo);

        Vehicule vehiculeSo2 = new Vehicule("VF-133-ZE", "Ford", 1999, 3, true,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/2018_Ford_Focus_ST-Line_EcoBoost_1.0.jpg/800px-2018_Ford_Focus_ST-Line_EcoBoost_1.0.jpg",
                Categorie.SUV, Dispo.Disponible);
        this.vehiculeRepo.save(vehiculeSo2);

        Vehicule vehiculeSo3 = new Vehicule("XS-975-HT", "Renault", 2001, 3, true,
                "https://img.autoplus.fr/picture/renault/clio/1535142/Renault_Clio_2019_17590-1600-1108.jpg?r",
                Categorie.SUV, Dispo.Disponible);
        this.vehiculeRepo.save(vehiculeSo3);

        Vehicule vehiculeSo4 = new Vehicule("XS-478-ER", "Renault", 2001, 3, true,
                "https://www.cdn.renault.com/content/dam/Renault/FR/webrender-fr/MEGANE/Life/FRAVP_XFB_1_BI_TEQNC_EA1%20A1M6R_RTOL16.jpg.ximg.m_12_m.smart.jpg",
                Categorie.SUV, Dispo.Disponible);
        this.vehiculeRepo.save(vehiculeSo4);

        // Création des réservations
        resaVehiculeRepo.save(new ResaVehicule(LocalDateTime.of(2019, 8, 26, 17, 30),
                LocalDateTime.of(2019, 9, 25, 12, 30), col2, vehiculeSo));
        resaVehiculeRepo.save(new ResaVehicule(LocalDateTime.of(2019, 8, 28, 11, 00),
                LocalDateTime.of(2019, 8, 31, 12, 30), col1, vehiculeSo2));
        resaVehiculeRepo.save(new ResaVehicule(LocalDateTime.of(2017, 12, 01, 11, 00),
                LocalDateTime.of(2017, 12, 01, 15, 00), col2, vehiculeSo3));
        resaVehiculeRepo.save(new ResaVehicule(LocalDateTime.of(2017, 01, 01, 11, 00),
                LocalDateTime.of(2017, 01, 11, 15, 00), col1, vehiculeSo3));

        // Création d'un jeu de donnée pour une reservation

        Itineraire itineraire = new Itineraire("Montpellier", "Nantes", "7h", 825);
        this.itineraireRepo.save(itineraire);

        Itineraire itineraire1 = new Itineraire("Nantes", "Montpellier", "7h", 825);
        this.itineraireRepo.save(itineraire1);

        Vehicule vehicule = new Vehicule("AB-344-CA", "Renault", 2008, 3, false,
                "https://www.cdn.renault.com/content/dam/Renault/FR/webrender-fr/MEGANE/Life/FRAVP_XFB_1_BI_TEQNC_EA1%20A1M6R_RTOL16.jpg.ximg.m_12_m.smart.jpg",
                Categorie.SUV, Dispo.Disponible);
        this.vehiculeRepo.save(vehicule);

        // Annonces
        AnnonceCovoit annonceCovoit = new AnnonceCovoit(col2, itineraire, vehicule,
                LocalDateTime.of(LocalDate.of(2019, 9, 3), LocalTime.of(8, 30)));
        this.annonceCovoitRepo.save(annonceCovoit);

        AnnonceCovoit annonceCovoit1 = new AnnonceCovoit(col2, itineraire1, vehicule,
                LocalDateTime.of(LocalDate.of(2019, 9, 14), LocalTime.of(8, 30)));
        this.annonceCovoitRepo.save(annonceCovoit1);

        AnnonceCovoit annonceCovoit2 = new AnnonceCovoit(col2, itineraire1, vehicule,
                LocalDateTime.of(LocalDate.of(2019, 8, 3), LocalTime.of(8, 30)));
        this.annonceCovoitRepo.save(annonceCovoit2);
        // Reservations
        ReservationCovoit reservationCovoit1 = new ReservationCovoit(annonceCovoit, col1);
        this.reservationCovoitRepo.save(reservationCovoit1);

        ReservationCovoit reservationCovoit2 = new ReservationCovoit(annonceCovoit1, col1);
        this.reservationCovoitRepo.save(reservationCovoit2);

        ReservationCovoit reservationCovoit3 = new ReservationCovoit(annonceCovoit2, col1);
        this.reservationCovoitRepo.save(reservationCovoit3);

        for (int i = 0; i < 15; i++) {
            this.reservationCovoitRepo
                    .save(new ReservationCovoit(this.annonceCovoitRepo.save(new AnnonceCovoit(col2, itineraire1,
                            vehicule, LocalDateTime.of(LocalDate.of(2019, 7, i + 1), LocalTime.of(8, 30)))), col1));
        }

    }

}
