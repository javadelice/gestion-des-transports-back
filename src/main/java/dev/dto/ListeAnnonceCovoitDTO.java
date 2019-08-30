package dev.dto;

import java.time.LocalDateTime;

import dev.domain.Itineraire;
import dev.domain.Statut;
import dev.domain.Vehicule;

public class ListeAnnonceCovoitDTO {
	
	 	private int id;
	    private CollegueDTO collegue;
	    private Itineraire itineraire;
	    private Vehicule vehicule;
	    private LocalDateTime dateTime;
	    private int nbVoyageurs; 
	    private Statut statut;


	    public ListeAnnonceCovoitDTO() {
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

		public int getNbVoyageurs() {
			return nbVoyageurs;
		}

		public void setNbVoyageurs(int nbVoyageurs) {
			this.nbVoyageurs = nbVoyageurs;
		}

		public Statut getStatut() {
			return statut;
		}

		public void setStatut(Statut statut) {
			this.statut = statut;
		}
		
}
