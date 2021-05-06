package com.example;

public class Description {
	private int num;
	private String id;
	private String nom;
	private String labele;
	private String descriptions;
	private double points;
	private String encouragement;
	private boolean clicked;
	
	public Description(int num, String id, String labele, String descriptions, double points, String encouragement,boolean clicked) {
		this.setNum(num);
		this.setId(id);
		this.setLabele(labele);
		this.setDescriptions(descriptions);
		this.setPoints(points);
		this.setEncouragement(encouragement);
		this.setClicked(clicked);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLabele() {
		return labele;
	}

	public void setLabele(String labele) {
		this.labele = labele;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public String getEncouragement() {
		return encouragement;
	}

	public void setEncouragement(String encouragement) {
		this.encouragement = encouragement;
	}
  public boolean getClicked(){
  return clicked;
  }
  public void setClicked(Boolean clicked){
    this.clicked = clicked;
  }

}