package dev.dto;


import dev.domain.ReservationCovoit;
import dev.domain.Statut;

public class ReservationCovoitDTO {
	
	private int id;
	private AnnonceCovoitDTO annonce;
	private Statut statutResa;
	
	public ReservationCovoitDTO(ReservationCovoit resa) {
		this.id = resa.getId();
		this.annonce = new AnnonceCovoitDTO();
		this.statutResa = resa.getStatutResa();		
	}
	
	
	public ReservationCovoitDTO(AnnonceCovoitDTO annonce, Statut statutResa) {
		super();
		this.annonce = annonce;
		this.statutResa = statutResa;
	}

	public AnnonceCovoitDTO getAnnonce() {
		return annonce;
	}

	public void setAnnonce(AnnonceCovoitDTO annonce) {
		this.annonce = annonce;
	}

	public Statut getStatutResa() {
		return statutResa;
	}

	public void setStatutResa(Statut statutResa) {
		this.statutResa = statutResa;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
