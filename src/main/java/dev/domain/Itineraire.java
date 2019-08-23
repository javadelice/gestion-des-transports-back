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
	private String adresseDestination;
	private String dureeDeTrajet;
	private Integer distance;

	public Itineraire() {
	}

	public Itineraire(String adresseDepart, String adresseDestination, String dureeDeTrajet, Integer distance) {
		this.adresseDepart = adresseDepart;
		this.adresseDestination = adresseDestination;
		this.dureeDeTrajet = "3h54";
		this.distance = 356;
	}

	public String getAdresseDepart() {
		return adresseDepart;
	}

	public void setAdresseDepart(String adresseDepart) {
		this.adresseDepart = adresseDepart;
	}

	public String getAdresseDestination() {
		return adresseDestination;
	}

	public void setAdresseDestination(String adresseDestination) {
		this.adresseDestination = adresseDestination;
	}

	public String getDureeDeTrajet() {
		return dureeDeTrajet;
	}

	public void setDureeDeTrajet(String dureeDeTrajet) {
		this.dureeDeTrajet = dureeDeTrajet;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Itineraire [adresseDepart=");
		builder.append(adresseDepart);
		builder.append(", adresseDestination=");
		builder.append(adresseDestination);
		builder.append(", dureeDeTrajet=");
		builder.append(dureeDeTrajet);
		builder.append(", distance=");
		builder.append(distance);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresseDepart == null) ? 0 : adresseDepart.hashCode());
		result = prime * result + ((adresseDestination == null) ? 0 : adresseDestination.hashCode());
		result = prime * result + ((distance == null) ? 0 : distance.hashCode());
		result = prime * result + ((dureeDeTrajet == null) ? 0 : dureeDeTrajet.hashCode());
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
		Itineraire other = (Itineraire) obj;
		if (adresseDepart == null) {
			if (other.adresseDepart != null)
				return false;
		} else if (!adresseDepart.equals(other.adresseDepart))
			return false;
		if (adresseDestination == null) {
			if (other.adresseDestination != null)
				return false;
		} else if (!adresseDestination.equals(other.adresseDestination))
			return false;
		if (distance == null) {
			if (other.distance != null)
				return false;
		} else if (!distance.equals(other.distance))
			return false;
		if (dureeDeTrajet == null) {
			if (other.dureeDeTrajet != null)
				return false;
		} else if (!dureeDeTrajet.equals(other.dureeDeTrajet))
			return false;
		return true;
	}

}
