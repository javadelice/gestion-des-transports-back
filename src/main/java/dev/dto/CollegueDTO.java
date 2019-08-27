package dev.dto;

import dev.domain.Collegue;

public class CollegueDTO {
    private String nomComplet;
    private String email;

    
    
    public CollegueDTO() {
		super();
	}

	public CollegueDTO(Collegue collegue) {
        this.nomComplet = collegue.getNom()+" "+collegue.getPrenom();
        this.email = collegue.getEmail();
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}
