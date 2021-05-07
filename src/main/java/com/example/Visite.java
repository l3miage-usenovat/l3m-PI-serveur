package com.example;

public class Visite {
	private String visiteid;
	private String defiid;
	private String visiteur;
	private String datevisite;
	private String modevisite;
	private String status;
	private int score;
	private int temps;
	private String evalution;
	private String commentaire;
	
	
	public Visite(String visiteId,String defiId,String  visiteur,String dateVisite,String modeVisite,String status,
			int score,int temps,String evalution,String commentaire) {
		
		this.setVisiteId(visiteId);
		this.setDefiId(defiId);
		this.setVisiteur(visiteur);
		this.setDateVisite(dateVisite);
		this.setModeVisite(modeVisite);
		this.setStatus(status);
		this.setScore(score);
		this.setTemps(temps);
		this.setEvalution(evalution);
		this.setCommentaire(commentaire);
		
	}


	public String getVisiteId() {
		return visiteid;
	}


	public void setVisiteId(String visiteId) {
		this.visiteid = visiteId;
	}


	public String getVisiteur() {
		return visiteur;
	}


	public void setVisiteur(String visiteur) {
		this.visiteur = visiteur;
	}


	public String getModeVisite() {
		return modevisite;
	}


	public void setModeVisite(String modeVisite) {
		this.modevisite = modeVisite;
	}


	public String getDefiId() {
		return defiid;
	}


	public void setDefiId(String defiId) {
		this.defiid = defiId;
	}


	public String getDateVisite() {
		return datevisite;
	}


	public void setDateVisite(String dateVisite) {
		this.datevisite = dateVisite;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public String getEvalution() {
		return evalution;
	}


	public void setEvalution(String evalution) {
		this.evalution = evalution;
	}


	public String getCommentaire() {
		return commentaire;
	}


	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}


	public int getTemps() {
		return temps;
	}


	public void setTemps(int temps) {
		this.temps = temps;
	}

}
