package dev.domain;

import javax.persistence.*;

@Entity
public class ReservationCovoit {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    private AnnonceCovoit annonceCovoit;
    @ManyToOne
    private Collegue passagers;
    
    @Enumerated(EnumType.STRING)
    private Statut statutResa;


    public ReservationCovoit() {
    }

    public ReservationCovoit(AnnonceCovoit annonceCovoit, Collegue passagers) {
        this.annonceCovoit = annonceCovoit;
        this.passagers = passagers;
        this.statutResa = statutResa.STATUT_ENCOURS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AnnonceCovoit getAnnonceCovoit() {
        return annonceCovoit;
    }

    public void setAnnonceCovoit(AnnonceCovoit annonceCovoit) {
        this.annonceCovoit = annonceCovoit;
    }

    public Collegue getPassagers() {
        return passagers;
    }

    public void setPassagers(Collegue passagers) {
        this.passagers = passagers;
    }

	public Statut getStatutResa() {
		return statutResa;
	}

	public void setStatutResa(Statut statutResa) {
		this.statutResa = statutResa;
	}
    
    
    
    
}
