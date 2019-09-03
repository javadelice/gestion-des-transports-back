package dev.dto;

import java.time.LocalDateTime;

public class ResaVehiculeLightDTO {

	private LocalDateTime dateDeDebut;
    private LocalDateTime dateDeFin;
    
    
	public ResaVehiculeLightDTO() {
		super();
	}


	public ResaVehiculeLightDTO(LocalDateTime dateDeDebut, LocalDateTime dateDeFin) {
		super();
		this.dateDeDebut = dateDeDebut;
		this.dateDeFin = dateDeFin;
	}


	public LocalDateTime getDateDeDebut() {
		return dateDeDebut;
	}


	public void setDateDeDebut(LocalDateTime dateDeDebut) {
		this.dateDeDebut = dateDeDebut;
	}


	public LocalDateTime getDateDeFin() {
		return dateDeFin;
	}


	public void setDateDeFin(LocalDateTime dateDeFin) {
		this.dateDeFin = dateDeFin;
	}
    
	
    
}
