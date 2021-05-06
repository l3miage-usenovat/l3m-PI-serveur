package com.example;


public class Defi {
    
    private String id;
    private String titre;
    private String dateDeCreation;
    private String auteur;
    private String description;
    private String type;
    private String dateDeModification;
    private String arret;
    private String distanciel;
    private String motCles;
    private int points;
    private String duree;
    private String indices;
    private String evaluation;
    private String epilogue;
    private double latitude;
    private double longitude;
    
    
    public Defi(String id,String titre,String dateDeCreation,String auteur,String description,String type,String dateDeModification,String arret,
    		String distanciel,String motCles,int points,String duree,String indices,String evaluation,String epilogue,double latitude,double longitude) {
    	this.setId(id);
    	this.setTitre(titre);
    	this.setDateDeCreation(dateDeCreation);
    	this.setAuteur(auteur);
    	this.setDescription(description);
    	this.setType(type);
    	this.setDateDeModification(dateDeModification);
    	this.setArret(arret);
    	this.setDistanciel(distanciel);
    	this.setMotCles(motCles);
    	this.setPoints(points);
    	this.setDuree(duree);
    	this.setIndices(indices);
    	this.setEvaluation(evaluation);
    	this.setEpilogue(epilogue);
    	this.setLatitude(latitude);
    	this.setLongitude(longitude);
    	
    }
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getDateDeCreation() {
		return dateDeCreation;
	}

	public void setDateDeCreation(String dateDeCreation) {
		this.dateDeCreation = dateDeCreation;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getArret() {
		return arret;
	}

	public void setArret(String arret) {
		this.arret = arret;
	}

	public String getDateDeModification() {
		return dateDeModification;
	}

	public void setDateDeModification(String dateDeModification) {
		this.dateDeModification = dateDeModification;
	}

	public String getMotCles() {
		return motCles;
	}

	public void setMotCles(String motCles) {
		this.motCles = motCles;
	}

	public String getDistanciel() {
		return distanciel;
	}

	public void setDistanciel(String distanciel) {
		this.distanciel = distanciel;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getIndices() {
		return indices;
	}

	public void setIndices(String indices) {
		this.indices = indices;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public String getEpilogue() {
		return epilogue;
	}

	public void setEpilogue(String epilogue) {
		this.epilogue = epilogue;
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
