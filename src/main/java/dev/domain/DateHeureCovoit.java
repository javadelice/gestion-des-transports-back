package dev.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DateHeureCovoit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String heureDeDepart;
	private String dateDeDepart;

	public DateHeureCovoit(String heureDeDepart, String dateDeDepart) {
		super();
		this.heureDeDepart = heureDeDepart;
		this.dateDeDepart = dateDeDepart;
	}

	public String getHeureDeDepart() {
		return heureDeDepart;
	}

	public void setHeureDeDepart(String heureDeDepart) {
		this.heureDeDepart = heureDeDepart;
	}

	public String getDateDepart() {
		return dateDeDepart;
	}

	public void setDateDeDepart(String dateDeDepart) {
		this.dateDeDepart = dateDeDepart;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DateHeureCovoit [heureDepart=");
		builder.append(heureDeDepart);
		builder.append(", dateDepart=");
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
		DateHeureCovoit other = (DateHeureCovoit) obj;
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
		return true;
	}

}
