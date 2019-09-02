package dev.dto;

import java.time.LocalDateTime;

import dev.domain.StatutResaChauffeur;
import dev.domain.Vehicule;

public class ResaVehiculeDTO {

    private Long idResaDto;
    private LocalDateTime dateDeDebut;
    private LocalDateTime dateDeFin;

    private Vehicule vehiculeSociete;
    private CollegueDTO chauffeur;
    private StatutResaChauffeur statut;

    public ResaVehiculeDTO() {

    }

    public Long getIdResaDto() {
        return idResaDto;
    }

    public ResaVehiculeDTO(Long idResaDto, LocalDateTime dateDeDebut, LocalDateTime dateDeFin, Vehicule vehiculeSociete,
            CollegueDTO chauffeur, StatutResaChauffeur statut) {
        this.idResaDto = idResaDto;
        this.dateDeDebut = dateDeDebut;
        this.dateDeFin = dateDeFin;
        this.vehiculeSociete = vehiculeSociete;
        this.chauffeur = chauffeur;
        this.statut = statut;
    }

    public ResaVehiculeDTO(LocalDateTime dateDeDebut, LocalDateTime dateDeFin, Vehicule vehiculeSociete,
            CollegueDTO chauffeur, StatutResaChauffeur statut) {
        this.dateDeDebut = dateDeDebut;
        this.dateDeFin = dateDeFin;
        this.vehiculeSociete = vehiculeSociete;
        this.chauffeur = chauffeur;
        this.statut = statut;
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

    public CollegueDTO getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(CollegueDTO chauffeur) {
        this.chauffeur = chauffeur;
    }

    public StatutResaChauffeur getStatut() {
        return statut;
    }

    public void setStatut(StatutResaChauffeur statut) {
        this.statut = statut;
    }

}
