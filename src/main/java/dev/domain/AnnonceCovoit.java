package dev.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AnnonceCovoit {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    private Collegue conducteur;
    
    @ManyToOne
    private Itineraire itineraire;
    
    @ManyToOne
    private Vehicule vehicule;
    
    private LocalDateTime dateTime;
   

    public AnnonceCovoit() {
    }

    public AnnonceCovoit(Collegue conducteur, Itineraire itineraire, Vehicule vehicule, LocalDateTime dateTime) {
        this.conducteur = conducteur;
        this.itineraire = itineraire;
        this.vehicule = vehicule;
        this.dateTime = dateTime;
    }
    

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collegue getConducteur() {
        return conducteur;
    }

    public void setConducteur(Collegue conducteur) {
        this.conducteur = conducteur;
    }

    public Itineraire getItineraire() {
        return itineraire;
    }

    public void setItineraire(Itineraire itineraire) {
        this.itineraire = itineraire;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    
}
