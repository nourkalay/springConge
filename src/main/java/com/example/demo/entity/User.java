package com.example.demo.entity;



import io.micrometer.common.lang.NonNull;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "utilisateurs")
public  class User {
	private static final long serialVersionUID=1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int id;
	 @NonNull
    private String nom;
	 @NonNull
    private String prenom;
	 @NonNull
    private String email;
    @NonNull
    private String password;
   
    
	public User( String nom, String prenom, String email, String password) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
	}
    
    public User() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
    
	

}
