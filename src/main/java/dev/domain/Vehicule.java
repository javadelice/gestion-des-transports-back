package dev.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public abstract class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehiSociete;
    private String immatriculation;
    private String marque;
    private int modele;
    private String categorie;

    public Vehicule() {
    }

    public Vehicule(Long idVehiSociete, String immatriculation, String marque, int modele, String categorie) {
        this.idVehiSociete = idVehiSociete;
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.categorie = categorie;
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

    public Long getIdVehiSociete() {
        return idVehiSociete;
    }

    public void setIdVehiSociete(Long idVehiSociete) {
        this.idVehiSociete = idVehiSociete;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Vehicule [idVehiSociete=");
        builder.append(idVehiSociete);
        builder.append(", immatriculation=");
        builder.append(immatriculation);
        builder.append(", marque=");
        builder.append(marque);
        builder.append(", modele=");
        builder.append(modele);
        builder.append(", categorie=");
        builder.append(categorie);
        builder.append("]");
        return builder.toString();
    }

}
