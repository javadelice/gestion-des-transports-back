package dev.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AnnonceCovoit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="conducteur_id")
    private Collegue conducteur;
    @ManyToOne 
    private Itineraire itineraire;
    @ManyToOne
    private Vehicule vehicule;
    private LocalDateTime dateTime;
    
    @Enumerated(EnumType.STRING)
    private Statut statut; 

    public AnnonceCovoit() {
    }

    public AnnonceCovoit(Collegue conducteur, Itineraire itineraire, Vehicule vehicule, LocalDateTime dateTime) {
        this.conducteur = conducteur;
        this.itineraire = itineraire;
        this.vehicule = vehicule;
        this.dateTime = dateTime;
        this.statut = statut.STATUT_ENCOURS;
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

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}
    
    
}
