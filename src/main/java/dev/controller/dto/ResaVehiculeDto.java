package dev.controller.dto;

import java.time.LocalDateTime;

public class ResaVehiculeDto {

    private Long idResaDto;
    private String immatriculation;
    private String marque;
    private String modele;
    private LocalDateTime dateDeDebut;
    private LocalDateTime dateDeFin;

    public ResaVehiculeDto() {

    }

    public ResaVehiculeDto(Long idResaDto, String immatriculation, String marque, String modele,
            LocalDateTime dateDeDebut, LocalDateTime dateDeFin) {
        this.idResaDto = idResaDto;
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.dateDeDebut = dateDeDebut;
        this.dateDeFin = dateDeFin;
    }

    public Long getIdResaDto() {
        return idResaDto;
    }

    public void setIdResaDto(Long idResaDto) {
        this.idResaDto = idResaDto;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
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
