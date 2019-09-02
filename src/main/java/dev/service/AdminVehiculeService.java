package dev.service;

import dev.domain.Categorie;
import dev.domain.Vehicule;
import dev.exception.VehiculeInvalideException;
import dev.repository.VehiculeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AdminVehiculeService {

    @Autowired
    VehiculeRepo vehiculeRepo;


    public List<Vehicule> getVehiculesSociete() {
        return vehiculeRepo.getVehiculesByEstSociete(true).orElse(new ArrayList<>());
    }

    public void checkVehicule(Vehicule vehicule) {
        //  Tous les véhicules de la société ont été immatriculés après 2009. Le format de l'immatriculation attendue
        //  est donc XX-NNN-XX (sept caractères alphanumériques : deux lettres, trois chiffres et deux lettres,
        //  les trois parties étant séparées par des tirets).

        //Le champ Categorie correspond au segment automobile européen :
        //Micro-urbaines
        //Mini-citadines
        //Citadines polyvalentes
        //Compactes
        //Berlines Taille S
        //Berlines Taille M
        //Berlines Taille L
        //SUV, Tout-terrains et Pick-up

        //  Vérifier que la photoUrl commence bien par `http`
        //  Si une des règles ci-dessus n'est pas valide, générer une exception :
        // `VehiculeInvalideException`.
        Map<String, String> erreurs = new HashMap<>();
        if (vehicule.getImmatriculation() == null || vehicule.getImmatriculation().isEmpty())
            erreurs.put("immatriculation", "L'immatriculation est obligatoire");
        if (vehicule.getImmatriculation() != null) {
            Pattern p = Pattern.compile("[A-Z]{2}-[0-9]{3}-[A-Z]{2}");
            Matcher m = p.matcher(vehicule.getImmatriculation());
            if (!m.matches()) erreurs.put("immatriculation", "Immatriculation invalide");
            else {
                Optional<Vehicule> vehiculeE = this.vehiculeRepo.getVehiculeByImmatriculation(vehicule.getImmatriculation());
                vehiculeE.ifPresent(vehicule1 -> erreurs.put("immatriculation","Immatriculation déjà enregistrée"));
            }

        }
        if (vehicule.getMarque() == null || vehicule.getMarque().isEmpty())
            erreurs.put("marque", "La marque est obligatoire");
        if (vehicule.getModele() == 0)
            erreurs.put("modele", "Le modèle est obligatoire");
        if (vehicule.getCategorie() == null)
            erreurs.put("categorie", "La catégorie est obligatoire");
        if (vehicule.getCategorie() != null) {
            Categorie[] lesCategorie = Categorie.values();
            boolean exist = false;
            for (Categorie categorie : lesCategorie) {
                if (vehicule.getCategorie().equals(categorie))
                    exist = true;
            }
            if (!exist)
                erreurs.put("categorie", "La catégorie est invalide");
        }
        if (vehicule.getNbPlaceDispo() == 0 || vehicule.getNbPlaceDispo() > 20)
            erreurs.put("nbPlace", "Le nombre de places disponible est obligatoire");
        if (!vehicule.getPhotoUrl().startsWith("http"))
            erreurs.put("photoUrl", "url de la photo invalide");

        if (!erreurs.isEmpty()) throw new VehiculeInvalideException(erreurs);

        this.addVehiculeSociete(vehicule);
    }

    private void addVehiculeSociete(Vehicule vehicule) {
        this.vehiculeRepo.save(vehicule);
    }
}
