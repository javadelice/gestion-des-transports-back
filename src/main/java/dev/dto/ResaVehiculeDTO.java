package dev.dto;

import java.time.LocalDateTime;

import dev.domain.Vehicule;

public class ResaVehiculeDTO {

    private Long idResaDto;
    private LocalDateTime dateDeDebut;
    private LocalDateTime dateDeFin;

    private Vehicule vehiculeSociete;

    public ResaVehiculeDTO() {

    }

    public ResaVehiculeDTO(Long idResaDto, LocalDateTime dateDeDebut, LocalDateTime dateDeFin,
            Vehicule vehiculeSociete) {
        this.idResaDto = idResaDto;
        this.dateDeDebut = dateDeDebut;
        this.dateDeFin = dateDeFin;
        this.vehiculeSociete = vehiculeSociete;
    }

    public ResaVehiculeDTO(LocalDateTime dateDeDebut, LocalDateTime dateDeFin, Vehicule vehiculeSociete) {
        this.dateDeDebut = dateDeDebut;
        this.dateDeFin = dateDeFin;
        this.vehiculeSociete = vehiculeSociete;
    }

    public Long getIdResaDto() {
        return idResaDto;
    }

    public void setIdResaDto(Long idResaDto) {
        this.idResaDto = idResaDto;
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

    public Vehicule getVehiculeSociete() {
        return vehiculeSociete;
    }

    public void setVehiculeSociete(Vehicule vehiculeSociete) {
        this.vehiculeSociete = vehiculeSociete;
    }

}
