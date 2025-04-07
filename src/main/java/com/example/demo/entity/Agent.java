package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity

public class Agent extends User{
	private static final long serialVersionUID=1L;

	
	@OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    private List<DemandeConge> demandesConge;
	public Agent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Agent( String nom, String prenom, String email, String password) {
		super( nom, prenom, email, password);
		// TODO Auto-generated constructor stub
	}
	public Agent(int id) {
		this.id=id;
	}
	
	
	

}
