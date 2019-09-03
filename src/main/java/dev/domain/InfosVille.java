package dev.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table (name="InfosVille")
public class InfosVille {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "ville_id")

	private Long id;

	@JoinColumn(name = "ville_nom")
	private String ville;

	@JoinColumn(name = "ville_longitude_deg")
	private double longitude;

	@JoinColumn(name = "ville_latitude_deg")
	private double latitude;

	public InfosVille() {}

	public InfosVille(String ville, double longitude, double latitude) {
		super();
		this.ville = ville;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

}
