package com.example.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class employee extends User {
	private static final long serialVersionUID=1L;
    private float soldeConge;
    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private List<DemandeConge> demandesConge;

	public employee() {
		super();
		
	}

	public employee(Long id, String nom, String prenom, String email, String password,float soldeConge) {
		super(id, nom, prenom, email, password);
		this.soldeConge=soldeConge;
	}

	public float getSoldeConge() {
		return soldeConge;
	}

	public void setSoldeConge(float soldeConge) {
		this.soldeConge = soldeConge;
	}
	

}
