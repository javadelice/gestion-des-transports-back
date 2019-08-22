package dev.dto;

import dev.domain.Collegue;

public class CollegueDTO {
    private String nomComplet;

    public CollegueDTO(Collegue collegue) {
        this.nomComplet = collegue.getNom()+" "+collegue.getPrenom();
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }
}
