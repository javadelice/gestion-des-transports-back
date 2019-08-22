package dev.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class AnnonceCovoit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/*
	 * @ManyToOne private Collegue conducteur;
	 */

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "itineraire_id")
	private Itineraire itineraire;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "vehiucle_id")
	private Vehicule vehicule;

	private LocalTime heureDeDepart;
	private LocalDate dateDeDepart;

	public AnnonceCovoit() {
	}

	public AnnonceCovoit(Itineraire itineraire, Vehicule vehicule, LocalDate dateDeDepart, LocalTime heureDeDepart) {
		super();
		this.itineraire = itineraire;
		this.vehicule = vehicule;
		this.dateDeDepart = dateDeDepart;
		this.heureDeDepart = heureDeDepart;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Itineraire getItineraire() {
		return itineraire;
	}

	public void setItineraire(Itineraire itineraire) {
		this.itineraire = itineraire;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public LocalTime getHeureDeDepart() {
		return heureDeDepart;
	}

	public void setHeureDeDepart(LocalTime heureDeDepart) {
		this.heureDeDepart = heureDeDepart;
	}

	public LocalDate getDateDeDepart() {
		return dateDeDepart;
	}

	public void setDateDeDepart(LocalDate dateDeDepart) {
		this.dateDeDepart = dateDeDepart;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AnnonceCovoit [id=");
		builder.append(id);
		builder.append(", itineraire=");
		builder.append(itineraire);
		builder.append(", vehicule=");
		builder.append(vehicule);
		builder.append(", heureDeDepart=");
		builder.append(heureDeDepart);
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
		result = prime * result + ((heureDeDepart == null) ? 0 : heureDeDepart.hashCode());
		result = prime * result + id;
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
		if (heureDeDepart == null) {
			if (other.heureDeDepart != null)
				return false;
		} else if (!heureDeDepart.equals(other.heureDeDepart))
			return false;
		if (id != other.id)
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
