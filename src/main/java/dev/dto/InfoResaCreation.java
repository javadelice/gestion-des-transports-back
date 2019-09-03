package dev.dto;

import dev.domain.Vehicule;

public class InfoResaCreation {

    private String heureDepart;
    private String minuteDepart;
    private String dateDepart;
    private String heureRetour;
    private String minuteRetour;
    private String dateRetour;
    private Vehicule vehiculeSociete;
    private boolean avecChauffeur;

    public InfoResaCreation() {
    }

    public InfoResaCreation(String heureDepart, String minuteDepart, String dateDepart, String heureRetour,
            String minuteRetour, String dateRetour, Vehicule vehiculeSociete, boolean avecChauffeur) {
        this.heureDepart = heureDepart;
        this.minuteDepart = minuteDepart;
        this.dateDepart = dateDepart;
        this.heureRetour = heureRetour;
        this.minuteRetour = minuteRetour;
        this.dateRetour = dateRetour;
        this.vehiculeSociete = vehiculeSociete;
        this.avecChauffeur = avecChauffeur;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public String getMinuteDepart() {
        return minuteDepart;
    }

    public void setMinuteDepart(String minuteDepart) {
        this.minuteDepart = minuteDepart;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getHeureRetour() {
        return heureRetour;
    }

    public void setHeureRetour(String heureRetour) {
        this.heureRetour = heureRetour;
    }

    public String getMinuteRetour() {
        return minuteRetour;
    }

    public void setMinuteRetour(String minuteRetour) {
        this.minuteRetour = minuteRetour;
    }

    public String getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(String dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Vehicule getVehiculeSociete() {
        return vehiculeSociete;
    }

    public void setVehiculeSociete(Vehicule vehiculeSociete) {
        this.vehiculeSociete = vehiculeSociete;
    }

    public boolean isAvecChauffeur() {
        return avecChauffeur;
    }

    public void setAvecChauffeur(boolean avecChauffeur) {
        this.avecChauffeur = avecChauffeur;
    }

}
