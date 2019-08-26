package dev.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Vehicule")
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
    @JoinColumn(name = "collegue_id")
    private Collegue passager;

    @OneToMany(mappedBy = "vehiculeSociete", cascade = CascadeType.PERSIST)
    private VehiculeSociete vehiculeSociete;

    public ResaVehicule() {
    }

    public ResaVehicule(Long idResV, LocalDateTime dateDebutResaV, LocalDateTime dateFinResV, Collegue passager,
            VehiculeSociete vehiculeSociete) {
        super();
        this.idResV = idResV;
        this.dateDebutResaV = dateDebutResaV;
        this.dateFinResV = dateFinResV;
        this.passager = passager;
        this.vehiculeSociete = vehiculeSociete;
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

    public VehiculeSociete getVehiculeSociete() {
        return vehiculeSociete;
    }

    public void setVehiculeSociete(VehiculeSociete vehiculeSociete) {
        this.vehiculeSociete = vehiculeSociete;
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
        builder.append(", vehiculeSociete=");
        builder.append(vehiculeSociete);
        builder.append("]");
        return builder.toString();
    }

}
