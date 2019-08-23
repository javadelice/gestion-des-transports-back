package dev;

import dev.domain.Collegue;
import dev.domain.Role;
import dev.domain.RoleCollegue;
import dev.domain.Version;
import dev.repository.CollegueRepo;
import dev.repository.VersionRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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

    public StartupListener(@Value("${app.version}") String appVersion, VersionRepo versionRepo, PasswordEncoder passwordEncoder, CollegueRepo collegueRepo) {
        this.appVersion = appVersion;
        this.versionRepo = versionRepo;
        this.passwordEncoder = passwordEncoder;
        this.collegueRepo = collegueRepo;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onStart() {
        this.versionRepo.save(new Version(appVersion));

        // Création de deux utilisateurs

        Collegue col1 = new Collegue();
        col1.setMatricule("1234-5678");
        col1.setNom("Admin");
        col1.setPrenom("DEV");
        col1.setEmail("admin@dev.fr");
        col1.setMotDePasse(passwordEncoder.encode("superpass"));
        col1.setRoles(Arrays.asList(new RoleCollegue(col1, Role.ROLE_ADMINISTRATEUR), new RoleCollegue(col1, Role.ROLE_COLLABORATEUR)));
        this.collegueRepo.save(col1);

        Collegue col2 = new Collegue();
        col2.setMatricule("0000-0000");
        col2.setNom("User");
        col2.setPrenom("DEV");
        col2.setEmail("user@dev.fr");
        col2.setMotDePasse(passwordEncoder.encode("superpass"));
        col2.setRoles(Arrays.asList(new RoleCollegue(col2, Role.ROLE_COLLABORATEUR)));
        this.collegueRepo.save(col2);
        
        Collegue col3 = new Collegue();
        col3.setMatricule("0000-0000");
        col3.setNom("Dupont");
        col3.setPrenom("Robert");
        col3.setEmail("robert.dupont@dev.fr");
        col3.setMotDePasse(passwordEncoder.encode("superpass"));
        col3.setRoles(Arrays.asList(new RoleCollegue(col3, Role.ROLE_CHAUFFEUR)));
        this.collegueRepo.save(col3);
        
        Collegue col4 = new Collegue();
        col4.setMatricule("0000-0000");
        col4.setNom("Dupont");
        col4.setPrenom("Martine");
        col4.setEmail("martine.dupont@dev.fr");
        col4.setMotDePasse(passwordEncoder.encode("superpass"));
        col4.setRoles(Arrays.asList(new RoleCollegue(col4, Role.ROLE_COLLABORATEUR)));
        this.collegueRepo.save(col4);
        
        Collegue col5 = new Collegue();
        col5.setMatricule("0000-0000");
        col5.setNom("Bertrand");
        col5.setPrenom("Martine");
        col5.setEmail("martine.bertrand@dev.fr");
        col5.setMotDePasse(passwordEncoder.encode("superpass"));
        col5.setRoles(Arrays.asList(new RoleCollegue(col5, Role.ROLE_CHAUFFEUR)));
        this.collegueRepo.save(col5);
    }

}
