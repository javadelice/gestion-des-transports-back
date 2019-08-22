package dev.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Vehicule")
public class ResaVehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idResV;

    private LocalDateTime dateDebutResaV;
    private LocalDateTime dateFinResV;
    private String immatriculation;
    private String marque;
    private int modele;

    public ResaVehicule() {
    }

    public ResaVehicule(LocalDateTime dateDebutResaV, LocalDateTime dateFinResV, String immatriculation, String marque,
            int modele) {
        this.dateDebutResaV = dateDebutResaV;
        this.dateFinResV = dateFinResV;
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
    }

    
    
    
    public ResaVehicule(Integer idResV, LocalDateTime dateDebutResaV, LocalDateTime dateFinResV, String immatriculation,
            String marque, int modele) {
        this.idResV = idResV;
        this.dateDebutResaV = dateDebutResaV;
        this.dateFinResV = dateFinResV;
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
    }

    public int getIdResV() {
        return idResV;
    }

    public void setIdResV(int idResV) {
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

    public int getModele() {
        return modele;
    }

    public void setModele(int modele) {
        this.modele = modele;
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
        builder.append(", immatriculation=");
        builder.append(immatriculation);
        builder.append(", marque=");
        builder.append(marque);
        builder.append(", modele=");
        builder.append(modele);
        builder.append("]");
        return builder.toString();
    }

}
