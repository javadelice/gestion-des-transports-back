package dev.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String immatriculation;
    private String marque;
    private int modele;
    private int nbPlaceDispo;
    private boolean estSociete;

    public Vehicule() {
    }

	public Vehicule(String immatriculation, String marque, int modele, int nbPlaceDispo) {
		this.immatriculation = immatriculation;
		this.marque = marque;
		this.modele = modele;
		this.nbPlaceDispo = nbPlaceDispo;
	}
    public Vehicule(Long id, String immatriculation, String marque, int modele, int nbPlaceDispo, boolean estSociete) {
        this.id = id;
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.nbPlaceDispo = nbPlaceDispo;
        this.estSociete = estSociete;
    }

    public Vehicule(String immatriculation, String marque, int modele, int nbPlaceDispo, boolean estSociete) {
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.nbPlaceDispo = nbPlaceDispo;
        this.estSociete = estSociete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getNbPlaceDispo() {
        return nbPlaceDispo;
    }

    public void setNbPlaceDispo(int nbPlaceDispo) {
        this.nbPlaceDispo = nbPlaceDispo;
    }

    public boolean isEstSociete() {
        return estSociete;
    }

    public void setEstSociete(boolean estSociete) {
        this.estSociete = estSociete;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Vehicule [id=");
        builder.append(id);
        builder.append(", immatriculation=");
        builder.append(immatriculation);
        builder.append(", marque=");
        builder.append(marque);
        builder.append(", modele=");
        builder.append(modele);
        builder.append(", nbPlaceDispo=");
        builder.append(nbPlaceDispo);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((immatriculation == null) ? 0 : immatriculation.hashCode());
        result = prime * result + ((marque == null) ? 0 : marque.hashCode());
        result = prime * result + modele;
        result = prime * result + nbPlaceDispo;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vehicule other = (Vehicule) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (immatriculation == null) {
            if (other.immatriculation != null)
                return false;
        } else if (!immatriculation.equals(other.immatriculation))
            return false;
        if (marque == null) {
            if (other.marque != null)
                return false;
        } else if (!marque.equals(other.marque))
            return false;
        if (modele != other.modele)
            return false;
        if (nbPlaceDispo != other.nbPlaceDispo)
            return false;
        return true;
    }

}
