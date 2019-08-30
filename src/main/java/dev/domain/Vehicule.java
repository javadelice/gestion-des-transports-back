package dev.domain;

import javax.persistence.*;

@Entity
@Table
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String immatriculation;
    private String marque;
    private int modele;
    private int nbPlaceDispo;
    private boolean estSociete;
    private String photoUrl;
    @Enumerated(EnumType.STRING)
    private Categorie categorie;
    @Transient
    private Dispo indisponible;

    public Vehicule() {
    }

    public Vehicule(Long id, String immatriculation, String marque, int modele, int nbPlaceDispo, boolean estSociete,
            String photoUrl, Categorie categorie, Dispo indisponible) {
        this.id = id;
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.nbPlaceDispo = nbPlaceDispo;
        this.estSociete = estSociete;
        this.photoUrl = photoUrl;
        this.categorie = categorie;
        this.indisponible = indisponible;
    }

    public Vehicule(String immatriculation, String marque, int modele, int nbPlaceDispo, boolean estSociete,
            String photoUrl, Categorie categorie, Dispo indisponible) {
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.nbPlaceDispo = nbPlaceDispo;
        this.estSociete = estSociete;
        this.photoUrl = photoUrl;
        this.categorie = categorie;
        this.indisponible = indisponible;
    }

    public Vehicule(String immatriculation, String marque, int modele, int nbPlaceDispo){
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.nbPlaceDispo = nbPlaceDispo;
        this.estSociete = false;
        this.photoUrl = "";
        this.categorie = Categorie.Citadines_polyvalentes;
        this.indisponible = null;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Dispo getIndisponible() {
        return indisponible;
    }

    public void setIndisponible(Dispo indisponible) {
        this.indisponible = indisponible;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((categorie == null) ? 0 : categorie.hashCode());
        result = prime * result + (estSociete ? 1231 : 1237);
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((immatriculation == null) ? 0 : immatriculation.hashCode());
        result = prime * result + ((indisponible == null) ? 0 : indisponible.hashCode());
        result = prime * result + ((marque == null) ? 0 : marque.hashCode());
        result = prime * result + modele;
        result = prime * result + nbPlaceDispo;
        result = prime * result + ((photoUrl == null) ? 0 : photoUrl.hashCode());
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
        if (categorie != other.categorie)
            return false;
        if (estSociete != other.estSociete)
            return false;
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
        if (indisponible != other.indisponible)
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
        if (photoUrl == null) {
            if (other.photoUrl != null)
                return false;
        } else if (!photoUrl.equals(other.photoUrl))
            return false;
        return true;
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
        builder.append(", estSociete=");
        builder.append(estSociete);
        builder.append(", photoUrl=");
        builder.append(photoUrl);
        builder.append(", categorie=");
        builder.append(categorie);
        builder.append(", indisponible=");
        builder.append(indisponible);
        builder.append("]");
        return builder.toString();
    }

}
