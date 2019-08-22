package dev.domain;

public class InfoCovoit {

	private String adresseDepart;
	private String adresseDestination;
	private String dureeDeTrajet;
	private Integer distance;
	private String immatriculation;
	private String marque;
	private String modele;
	private Integer nombrePassager;
	private String heureDeDepart;
	private String dateDeDepart;

	public InfoCovoit() {
	}

	public InfoCovoit(String adresseDepart, String adresseDestination, String dureeDeTrajet, Integer distance, String immatriculation, String marque,
	        String modele, Integer nombrePassager, String heureDeDepart, String dateDeDepart) {
		super();
		this.adresseDepart = adresseDepart;
		this.adresseDestination = adresseDestination;
		this.dureeDeTrajet = dureeDeTrajet;
		this.distance = distance;
		this.immatriculation = immatriculation;
		this.marque = marque;
		this.modele = modele;
		this.nombrePassager = nombrePassager;
		this.heureDeDepart = heureDeDepart;
		this.dateDeDepart = dateDeDepart;
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

	public String getHeureDeDepart() {
		return heureDeDepart;
	}

	public void setHeureDeDepart(String heureDeDepart) {
		this.heureDeDepart = heureDeDepart;
	}

	public String getDateDeDepart() {
		return dateDeDepart;
	}

	public void setDateDeDepart(String dateDeDepart) {
		this.dateDeDepart = dateDeDepart;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InfoCovoit [adresseDepart=");
		builder.append(adresseDepart);
		builder.append(", adresseDestination=");
		builder.append(adresseDestination);
		builder.append(", dureeDeTrajet=");
		builder.append(dureeDeTrajet);
		builder.append(", distance=");
		builder.append(distance);
		builder.append(", immatriculation=");
		builder.append(immatriculation);
		builder.append(", marque=");
		builder.append(marque);
		builder.append(", modele=");
		builder.append(modele);
		builder.append(", nombrePassager=");
		builder.append(nombrePassager);
		builder.append(", heureDepart=");
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
		result = prime * result + ((adresseDepart == null) ? 0 : adresseDepart.hashCode());
		result = prime * result + ((adresseDestination == null) ? 0 : adresseDestination.hashCode());
		result = prime * result + ((dateDeDepart == null) ? 0 : dateDeDepart.hashCode());
		result = prime * result + ((distance == null) ? 0 : distance.hashCode());
		result = prime * result + ((dureeDeTrajet == null) ? 0 : dureeDeTrajet.hashCode());
		result = prime * result + ((heureDeDepart == null) ? 0 : heureDeDepart.hashCode());
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
		InfoCovoit other = (InfoCovoit) obj;
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
		if (dateDeDepart == null) {
			if (other.dateDeDepart != null)
				return false;
		} else if (!dateDeDepart.equals(other.dateDeDepart))
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
		if (heureDeDepart == null) {
			if (other.heureDeDepart != null)
				return false;
		} else if (!heureDeDepart.equals(other.heureDeDepart))
			return false;
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
