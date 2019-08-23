package dev.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class VehiculeSociete extends Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehiSociete;

    @ManyToOne
    @JoinColumn(name = "idResV")
    private List<ResaVehicule> resaVehicule;

    public VehiculeSociete() {
    }

    public VehiculeSociete(Long idVehiSociete, List<ResaVehicule> resaVehicule) {
        super();
        this.idVehiSociete = idVehiSociete;
        this.resaVehicule = resaVehicule;
    }

    public Long getIdVehiSociete() {
        return idVehiSociete;
    }

    public void setIdVehiSociete(Long idVehiSociete) {
        this.idVehiSociete = idVehiSociete;
    }

    public List<ResaVehicule> getResaVehicule() {
        return resaVehicule;
    }

    public void setResaVehicule(List<ResaVehicule> resaVehicule) {
        this.resaVehicule = resaVehicule;
    }

}
