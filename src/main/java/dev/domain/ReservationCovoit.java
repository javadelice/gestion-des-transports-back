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


    public ReservationCovoit() {
    }

    public ReservationCovoit(AnnonceCovoit annonceCovoit, Collegue passagers) {
        this.annonceCovoit = annonceCovoit;
        this.passagers = passagers;
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
}
