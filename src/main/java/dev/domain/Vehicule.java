package dev.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vehicule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String immatriculation;
	private String marque;
	private String modele;
	private Integer nombrePassager;

	public Vehicule() {

	}

	public Vehicule(String immatriculation, String marque, String modele, Integer nombrePassager) {
		super();
		this.immatriculation = immatriculation;
		this.marque = marque;
		this.modele = modele;
		this.nombrePassager = nombrePassager;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public Integer getNombrePassager() {
		return nombrePassager;
	}

	public void setNombrePassager(Integer nombrePassager) {
		this.nombrePassager = nombrePassager;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Vehicule [immatriculation=");
		builder.append(immatriculation);
		builder.append(", marque=");
		builder.append(marque);
		builder.append(", modele=");
		builder.append(modele);
		builder.append(", nombrePassager=");
		builder.append(nombrePassager);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((immatriculation == null) ? 0 : immatriculation.hashCode());
		result = prime * result + ((marque == null) ? 0 : marque.hashCode());
		result = prime * result + ((modele == null) ? 0 : modele.hashCode());
		result = prime * result + ((nombrePassager == null) ? 0 : nombrePassager.hashCode());
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
		Vehicule other = (Vehicule) obj;
		if (immatriculation == null) {
			if (other.immatriculation != null)
				return false;
		} else if (!immatriculation.equals(other.immatriculation))
			return false;
		if (marque == null) {
			if (other.marque != null)
				return false;
		} else if (!marque.equals(other.marque))
			return false;
		if (modele == null) {
			if (other.modele != null)
				return false;
		} else if (!modele.equals(other.modele))
			return false;
		if (nombrePassager == null) {
			if (other.nombrePassager != null)
				return false;
		} else if (!nombrePassager.equals(other.nombrePassager))
			return false;
		return true;
	}

}
