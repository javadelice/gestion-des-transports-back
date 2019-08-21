package dev.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Itineraire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   private String adresseDepart;
   private String adresseDest;
   private String duree;
   private float distance;

    public Itineraire() {
    }

    public Itineraire(String adresseDepart, String adresseDest, String duree, float distance) {
        this.adresseDepart = adresseDepart;
        this.adresseDest = adresseDest;
        this.duree = duree;
        this.distance = distance;
    }

    public String getAdresseDepart() {
        return adresseDepart;
    }

    public void setAdresseDepart(String adresseDepart) {
        this.adresseDepart = adresseDepart;
    }

    public String getAdresseDest() {
        return adresseDest;
    }

    public void setAdresseDest(String adresseDest) {
        this.adresseDest = adresseDest;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
