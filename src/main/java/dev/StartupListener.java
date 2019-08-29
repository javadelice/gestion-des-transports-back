package dev;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.domain.AnnonceCovoit;
import dev.domain.Collegue;
import dev.domain.Itineraire;
import dev.domain.ResaVehicule;
import dev.domain.ReservationCovoit;
import dev.domain.Role;
import dev.domain.RoleCollegue;
import dev.domain.Vehicule;
import dev.domain.Version;
import dev.repository.AnnonceCovoitRepo;
import dev.repository.CollegueRepo;
import dev.repository.ItineraireRepo;
import dev.repository.ResaVehiculeRepository;
import dev.repository.ReservationCovoitRepo;
import dev.repository.VehiculeRepo;
import dev.repository.VersionRepo;

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

    @Autowired
    private ResaVehiculeRepository resaVehiculeRepo;

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

        // Création d'utilisateurs

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

        Collegue col9 = new Collegue();
        col9.setNom("TestAnnonce");
        col9.setPrenom("VED");
        col9.setEmail("usertest@dev.fr");
        col9.setMotDePasse(passwordEncoder.encode("superpass"));
        col9.setRoles(Arrays.asList(new RoleCollegue(col9, Role.ROLE_UTILISATEUR)));
        this.collegueRepo.save(col9);

        Collegue col3 = new Collegue();
        col3.setNom("Dupont");
        col3.setPrenom("Robert");
        col3.setEmail("robert.dupont@dev.fr");
        col3.setMotDePasse(passwordEncoder.encode("superpass"));
        col3.setRoles(Arrays.asList(new RoleCollegue(col3, Role.ROLE_CHAUFFEUR)));
        this.collegueRepo.save(col3);

        Collegue col4 = new Collegue();
        col4.setNom("Dupont");
        col4.setPrenom("Martine");
        col4.setEmail("adchauvin44@gmail.com");
        col4.setMotDePasse(passwordEncoder.encode("superpass"));
        col4.setRoles(Arrays.asList(new RoleCollegue(col4, Role.ROLE_UTILISATEUR)));
        this.collegueRepo.save(col4);

        Collegue col5 = new Collegue();
        col5.setNom("Bertrand");
        col5.setPrenom("Martine");
        col5.setEmail("adrienchauvin@hotmail.fr");
        col5.setMotDePasse(passwordEncoder.encode("superpass"));
        col5.setRoles(Arrays.asList(new RoleCollegue(col5, Role.ROLE_UTILISATEUR)));
        this.collegueRepo.save(col5);
        
        Collegue col6 = new Collegue(); 
        col6.setNom("Jean-Michel"); 
        col6.setPrenom("FACILE");
        col6.setEmail("adrienchauvin44@gmail.com");
        col6.setMotDePasse(passwordEncoder.encode("superpass"));
        col6.setRoles(Arrays.asList(new RoleCollegue(col6, Role.ROLE_UTILISATEUR)));
        this.collegueRepo.save(col6);


        //Création véhicule de société
        Vehicule vehiculeSo = new Vehicule("AC-985-CA","Peugeot",2008,3, true);
        this.vehiculeRepo.save(vehiculeSo);

        Vehicule vehiculeSo2 = new Vehicule("VF-133-ZE","Ford",1999,3, true);
        this.vehiculeRepo.save(vehiculeSo2);

        Vehicule vehiculeSo3 = new Vehicule("XS-975-HT","Renault",2001,3, true);
        this.vehiculeRepo.save(vehiculeSo3);

        Vehicule vehiculeSo4 = new Vehicule("XS-975-HT","Renault",2001,3, true);
        this.vehiculeRepo.save(vehiculeSo4);

        //Création des réservations
        resaVehiculeRepo.save(new ResaVehicule(LocalDateTime.of(2019, 8, 26, 17, 30), LocalDateTime.of(2019, 9, 25, 12, 30), col2, vehiculeSo));
        resaVehiculeRepo.save(new ResaVehicule(LocalDateTime.of(2019, 8, 28, 11, 00), LocalDateTime.of(2019, 8, 31, 12, 30), col1, vehiculeSo2));
        resaVehiculeRepo.save(new ResaVehicule(LocalDateTime.of(2017, 12, 01, 11, 00), LocalDateTime.of(2017, 12, 01, 15, 00), col2, vehiculeSo3));
        resaVehiculeRepo.save(new ResaVehicule(LocalDateTime.of(2017, 01, 01, 11, 00), LocalDateTime.of(2017, 01, 11, 15, 00), col1, vehiculeSo3));

        Collegue col7 = new Collegue();
        col7.setNom("Chauffeur");
        col7.setPrenom("Michel");
        col7.setEmail("michou@dev.fr");
        col7.setMotDePasse(passwordEncoder.encode("s"));
        col7.setRoles(Arrays.asList(new RoleCollegue(col7, Role.ROLE_UTILISATEUR), new RoleCollegue(col7, Role.ROLE_CHAUFFEUR)));
        this.collegueRepo.save(col7);

        //Création d'un jeu de donnée pour une reservation

        Itineraire itineraire = new Itineraire("Montpellier","Nantes","7h",825);
        this.itineraireRepo.save(itineraire);

        Itineraire itineraire1 = new Itineraire("Nantes","Montpellier","7h",825);
        this.itineraireRepo.save(itineraire1);

        Itineraire itineraire2 = new Itineraire("Paris","Nantes","3h45",385);
        this.itineraireRepo.save(itineraire2);


        //Vehicules

        Vehicule vehicule = new Vehicule("AB-344-CA","Renault",2008,3, false);
        this.vehiculeRepo.save(vehicule);

        Vehicule vehicule2 = new Vehicule("DT-811-PP","Audi",2012,3,false);
        this.vehiculeRepo.save(vehicule2);

        //Annonces
        AnnonceCovoit annonceCovoit = new AnnonceCovoit(col2,itineraire,vehicule, LocalDateTime.of(LocalDate.of(2019,9,3), LocalTime.of(8,30)));
        this.annonceCovoitRepo.save(annonceCovoit);

        AnnonceCovoit annonceCovoit1 = new AnnonceCovoit(col2,itineraire1,vehicule,LocalDateTime.of(LocalDate.of(2019,9,14), LocalTime.of(8,30)));
        this.annonceCovoitRepo.save(annonceCovoit1);

        AnnonceCovoit annonceCovoit2 = new AnnonceCovoit(col2,itineraire1,vehicule,LocalDateTime.of(LocalDate.of(2019,8,3), LocalTime.of(8,30)));
        this.annonceCovoitRepo.save(annonceCovoit2);

        AnnonceCovoit annonceCovoit3 = new AnnonceCovoit(col4,itineraire2,vehicule2,LocalDateTime.of(LocalDate.of(2019,9,3), LocalTime.of(10,30)));
        this.annonceCovoitRepo.save(annonceCovoit3);

        AnnonceCovoit annonceCovoit4 = new AnnonceCovoit(col2,itineraire1,vehicule,LocalDateTime.of(LocalDate.of(2019,9,14), LocalTime.of(14,30)));
        this.annonceCovoitRepo.save(annonceCovoit4);


        //Reservations
        ReservationCovoit reservationCovoit1 = new ReservationCovoit(annonceCovoit,col1);
        this.reservationCovoitRepo.save(reservationCovoit1);

        ReservationCovoit reservationCovoit2 = new ReservationCovoit(annonceCovoit1,col1);
        this.reservationCovoitRepo.save(reservationCovoit2);

        ReservationCovoit reservationCovoit3 = new ReservationCovoit(annonceCovoit2,col1);
        this.reservationCovoitRepo.save(reservationCovoit3);

        ReservationCovoit reservationCovoit4 = new ReservationCovoit(annonceCovoit2, col3);
        this.reservationCovoitRepo.save(reservationCovoit4);

        for(int i = 0;i<15;i++){
            this.reservationCovoitRepo.save(
                    new ReservationCovoit(
                            this.annonceCovoitRepo.save(
                                    new AnnonceCovoit(col2,
                                            itineraire1,
                                            vehicule,
                                            LocalDateTime.of(LocalDate.of(2019,7,i+1),
                                                    LocalTime.of(8,30)))),
                            col1));
        }



    }

}
