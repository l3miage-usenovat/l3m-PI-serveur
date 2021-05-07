package com.example;

public class Visite {
	private String visiteId;
	private String defiId;
	private String visiteur;
	private String dateVisite;
	private String modeVisite;
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
		return visiteId;
	}


	public void setVisiteId(String visiteId) {
		this.visiteId = visiteId;
	}


	public String getVisiteur() {
		return visiteur;
	}


	public void setVisiteur(String visiteur) {
		this.visiteur = visiteur;
	}


	public String getModeVisite() {
		return modeVisite;
	}


	public void setModeVisite(String modeVisite) {
		this.modeVisite = modeVisite;
	}


	public String getDefiId() {
		return defiId;
	}


	public void setDefiId(String defiId) {
		this.defiId = defiId;
	}


	public String getDateVisite() {
		return dateVisite;
	}


	public void setDateVisite(String dateVisite) {
		this.dateVisite = dateVisite;
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
