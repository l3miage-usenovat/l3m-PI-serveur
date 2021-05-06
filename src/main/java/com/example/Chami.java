package com.example;

public class Chami {
	
	private String email;
    private String pseudo;
    private int age;
    private String ville;
    private String description;

    public Chami(String email,String pseudo,int age,String ville,String description){
		this.setEmail(email);
		this.setPseudo(pseudo);
    	this.setAge(age);
    	this.setVille(ville);
    	this.setDescription(description);

    }

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

    

}