package dev.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
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

    public ResaVehicule(LocalDateTime dateDebutResaV, LocalDateTime dateFinResV, Collegue passager,
            Vehicule vehicule) {
        this.dateDebutResaV = dateDebutResaV;
        this.dateFinResV = dateFinResV;
        this.passager = passager;
        this.vehicule = vehicule;
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
        builder.append("]");
        return builder.toString();
    }

}
