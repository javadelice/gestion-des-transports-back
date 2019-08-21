package dev.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class ReservationCovoit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private AnnonceCovoit annonceCovoit;
    @OneToMany
    private List<Collegue> passagers;

    public ReservationCovoit(AnnonceCovoit annonceCovoit, List<Collegue> passagers) {
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

    public List<Collegue> getPassagers() {
        return passagers;
    }

    public void setPassagers(List<Collegue> passagers) {
        this.passagers = passagers;
    }
}
