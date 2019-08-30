package dev.dto;


import dev.domain.ReservationCovoit;
import dev.domain.Statut;

public class ReservationCovoitDTO {
	
	private int idResa;
	private AnnonceCovoitDTO annonce;
	private Statut statutResa;
	
	public ReservationCovoitDTO(ReservationCovoit resa) {
		this.idResa = resa.getId();
		this.annonce = new AnnonceCovoitDTO();
		this.statutResa = resa.getStatutResa();		
	}
	
	
	public ReservationCovoitDTO(AnnonceCovoitDTO annonce, Statut statutResa) {
		super();
		this.annonce = annonce;
		this.statutResa = statutResa;
	}
	
	public ReservationCovoitDTO(AnnonceCovoitDTO annonce, Statut statutResa,int id) {
		this.annonce = annonce;
		this.statutResa = statutResa;
		this.idResa = id;
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
		return idResa;
	}


	public void setId(int id) {
		this.idResa = id;
	}
	
	
	
	
}
