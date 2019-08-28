package dev.dto;

import dev.domain.AnnonceCovoit;
import dev.domain.Itineraire;
import dev.domain.Vehicule;

import java.time.LocalDateTime;

public class AnnonceCovoitDTO {
    private int id;
    private CollegueDTO collegue;
    private Itineraire itineraire;
    private Vehicule vehicule;
    private LocalDateTime dateTime;
    private int nbPlacesLibres;

    public AnnonceCovoitDTO() {
    }
    
    public AnnonceCovoitDTO(AnnonceCovoit annonce) {
    	this.id = annonce.getId();
    	this.collegue = new CollegueDTO(annonce.getConducteur());
    	this.itineraire = annonce.getItineraire();
    	this.vehicule = annonce.getVehicule();
    	this.dateTime = annonce.getDateTime();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CollegueDTO getCollegue() {
        return collegue;
    }

    public void setCollegue(CollegueDTO collegue) {
        this.collegue = collegue;
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

	public int getNbPlacesLibres() {
		return nbPlacesLibres;
	}

	public void setNbPlacesLibres(int nbPlacesLibres) {
		this.nbPlacesLibres = nbPlacesLibres;
	}
    
    
}
