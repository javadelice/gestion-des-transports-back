package dev.domain;

public class InfoCovoit {

	private String adresseDepart;
	private String adresseDestination;
	private String duree;
	private float distance;
	private String immatriculation;
	private String marque;
	private String modele;
	private int nbPlaceDispo;
	private String heureDeDepart;
	private String minuteDeDepart;
	private String dateDeDepart;

	public InfoCovoit() {
	}

	public InfoCovoit(String adresseDepart, String adresseDestination, String immatriculation, String marque,
	        String modele, int nbPlaceDispo, String heureDeDepart, String minuteDeDepart, String dateDeDepart) {
		super();
		this.adresseDepart = adresseDepart;
		this.adresseDestination = adresseDestination;
		this.immatriculation = immatriculation;
		this.marque = marque;
		this.modele = modele;
		this.nbPlaceDispo = nbPlaceDispo;
		this.heureDeDepart = heureDeDepart;
		this.minuteDeDepart = minuteDeDepart;
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

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
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

	public int getNbPlaceDispo() {
		return nbPlaceDispo;
	}

	public void setNbPlaceDispo(int nbPlaceDispo) {
		this.nbPlaceDispo = nbPlaceDispo;
	}

	public String getHeureDeDepart() {
		return heureDeDepart;
	}

	public void setHeureDeDepart(String heureDeDepart) {
		this.heureDeDepart = heureDeDepart;
	}

	public String getMinuteDeDepart() {
		return minuteDeDepart;
	}

	public void setMinuteDeDepart() {
		this.minuteDeDepart = minuteDeDepart;
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
		builder.append(duree);
		builder.append(", distance=");
		builder.append(distance);
		builder.append(", immatriculation=");
		builder.append(immatriculation);
		builder.append(", marque=");
		builder.append(marque);
		builder.append(", modele=");
		builder.append(modele);
		builder.append(", nombrePassager=");
		builder.append(nbPlaceDispo);
		builder.append(", heureDeDepart=");
		builder.append(heureDeDepart);
		builder.append(", minuteDeDepart=");
		builder.append(minuteDeDepart);
		builder.append(", dateDeDepart=");
		builder.append(dateDeDepart);
		builder.append("]");
		return builder.toString();
	}

}
