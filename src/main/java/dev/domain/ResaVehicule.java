package dev.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ResaVehicule")
public class ResaVehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResV;

    private LocalDateTime dateDebutResaV;
    private LocalDateTime dateFinResV;

    /*
     * passager = collaborateur qui réserve
     */
    @ManyToOne
    private Collegue passager;
    @ManyToOne
    private Vehicule vehicule;
    @ManyToOne
    private Collegue chauffeur;
    @Enumerated(EnumType.STRING)
    private StatutResaChauffeur status;

    public ResaVehicule() {
    }

    public ResaVehicule(Long idResV, LocalDateTime dateDebutResaV, LocalDateTime dateFinResV, Collegue passager,
            Vehicule vehicule) {
        this.idResV = idResV;
        this.dateDebutResaV = dateDebutResaV;
        this.dateFinResV = dateFinResV;
        this.passager = passager;
        this.vehicule = vehicule;
    }

    public ResaVehicule(LocalDateTime dateDebutResaV, LocalDateTime dateFinResV, Collegue passager, Vehicule vehicule) {
        this.dateDebutResaV = dateDebutResaV;
        this.dateFinResV = dateFinResV;
        this.passager = passager;
        this.vehicule = vehicule;
    }

    public ResaVehicule(LocalDateTime dateDebutResaV, LocalDateTime dateFinResV, Collegue passager, Vehicule vehicule,
            Collegue chauffeur, StatutResaChauffeur status) {
        super();
        this.dateDebutResaV = dateDebutResaV;
        this.dateFinResV = dateFinResV;
        this.passager = passager;
        this.vehicule = vehicule;
        this.chauffeur = chauffeur;
        this.status = status;
    }

    public ResaVehicule(LocalDateTime dateDebutResaV, LocalDateTime dateFinResV, Collegue passager, Vehicule vehicule,
            StatutResaChauffeur status) {
        this.dateDebutResaV = dateDebutResaV;
        this.dateFinResV = dateFinResV;
        this.passager = passager;
        this.vehicule = vehicule;
        this.status = status;
    }

    public Long getIdResV() {
        return idResV;
    }

    public void setIdResV(Long idResV) {
        this.idResV = idResV;
    }

    public LocalDateTime getDateDebutResaV() {
        return dateDebutResaV;
    }

    public void setDateDebutResaV(LocalDateTime dateDebutResaV) {
        this.dateDebutResaV = dateDebutResaV;
    }

    public LocalDateTime getDateFinResV() {
        return dateFinResV;
    }

    public void setDateFinResV(LocalDateTime dateFinResV) {
        this.dateFinResV = dateFinResV;
    }

    public Collegue getPassager() {
        return passager;
    }

    public void setPassager(Collegue passager) {
        this.passager = passager;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public Collegue getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Collegue chauffeur) {
        this.chauffeur = chauffeur;
    }

    public StatutResaChauffeur getStatus() {
        return status;
    }

    public void setStatus(StatutResaChauffeur status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ResaVehicule [idResV=");
        builder.append(idResV);
        builder.append(", dateDebutResaV=");
        builder.append(dateDebutResaV);
        builder.append(", dateFinResV=");
        builder.append(dateFinResV);
        builder.append(", passager=");
        builder.append(passager);
        builder.append(", vehicule=");
        builder.append(vehicule);
        builder.append(", chauffeur=");
        builder.append(chauffeur);
        builder.append(", status=");
        builder.append(status);
        builder.append("]");
        return builder.toString();
    }

}
