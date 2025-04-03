package com.example.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "agent")
public class Agent extends User{
	private static final long serialVersionUID=1L;

	
	@OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    private List<DemandeConge> demandesConge;
	public Agent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Agent(Long id, String nom, String prenom, String email, String password) {
		super(id, nom, prenom, email, password);
		// TODO Auto-generated constructor stub
	}
	
	
	

}
