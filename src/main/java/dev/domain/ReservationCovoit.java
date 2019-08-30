package dev.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	private Statut statut;

	public ReservationCovoit() {
	}

	public ReservationCovoit(AnnonceCovoit annonceCovoit, Collegue passagers) {
		this.annonceCovoit = annonceCovoit;
		this.passagers = passagers;
		this.statut = statut.STATUT_ENCOURS;
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

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	
	

}
