package dev.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class AnnonceCovoit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private Collegue conducteur;
	@OneToOne
	private Itineraire itineraire;
	@OneToOne
	private Vehicule vehicule;
	private LocalDateTime dateDeDepart;

	public AnnonceCovoit() {
	}

	public AnnonceCovoit(Collegue conducteur, Itineraire itineraire, Vehicule vehicule, LocalDateTime dateDeDepart) {
		this.conducteur = conducteur;
		this.itineraire = itineraire;
		this.vehicule = vehicule;
		this.dateDeDepart = dateDeDepart;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public Itineraire getItineraire() {
		return itineraire;
	}

	public void setItineraire(Itineraire itineraire) {
		this.itineraire = itineraire;
	}

	public LocalDateTime getDateDeDepart() {
		return dateDeDepart;
	}

	public void setDateDeDepart(LocalDateTime dateDeDepart) {
		this.dateDeDepart = dateDeDepart;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Annonce [vehicule=");
		builder.append(vehicule);
		builder.append(", itineraire=");
		builder.append(itineraire);
		builder.append(", dateDeDepart=");
		builder.append(dateDeDepart);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDeDepart == null) ? 0 : dateDeDepart.hashCode());
		result = prime * result + ((itineraire == null) ? 0 : itineraire.hashCode());
		result = prime * result + ((vehicule == null) ? 0 : vehicule.hashCode());
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
		AnnonceCovoit other = (AnnonceCovoit) obj;
		if (dateDeDepart == null) {
			if (other.dateDeDepart != null)
				return false;
		} else if (!dateDeDepart.equals(other.dateDeDepart))
			return false;
		if (itineraire == null) {
			if (other.itineraire != null)
				return false;
		} else if (!itineraire.equals(other.itineraire))
			return false;
		if (vehicule == null) {
			if (other.vehicule != null)
				return false;
		} else if (!vehicule.equals(other.vehicule))
			return false;
		return true;
	}

	/*
	 * 
	 * LocalDateTime dateTimeInsert = LocalDateTime.of(year, month, day, hour, min,
	 * scd); DateTimeFormatter dtf =
	 * DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	 * dateTimeList.add(dateTimeInsert);
	 * System.out.println(dateTimeInsert.format(dtf));
	 * 
	 */

}
