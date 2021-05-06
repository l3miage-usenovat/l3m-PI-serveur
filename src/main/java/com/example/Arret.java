package com.example;

public class Arret {
	private String nom;
	private String code;
	private String streetmap;
	private String googlemap;
	private String ville;
	private double latitude;
	private double longitude;
	private String idDefis;
	
	public Arret(String nom,String code,String streetmap,String googlemap,
			String ville,double latitude,double longitude,String idDefis) {
		this.setNom(nom);
		this.setCode(code);
		this.setStreetmap(streetmap);
		this.setGooglemap(googlemap);
		this.setVille(ville);
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setIdDefis(idDefis);
		
		
	}

	public String getGooglemap() {
		return googlemap;
	}

	public void setGooglemap(String googlemap) {
		this.googlemap = googlemap;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getStreetmap() {
		return streetmap;
	}

	public void setStreetmap(String streetmap) {
		this.streetmap = streetmap;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getIdDefis() {
		return idDefis;
	}

	public void setIdDefis(String idDefis) {
		this.idDefis = idDefis;
	}
	

}
