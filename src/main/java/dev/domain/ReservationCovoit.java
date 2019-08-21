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




}
