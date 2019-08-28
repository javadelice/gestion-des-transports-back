package dev.dto;

import dev.domain.Statut;

public class ReservationCovoitDTO {
	
	private AnnonceCovoitDTO annonce;
	private Statut statutResa;
	
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
	
	
}
