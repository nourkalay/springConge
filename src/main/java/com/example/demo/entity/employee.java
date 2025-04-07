package com.example.demo.entity;

import java.util.List;

import com.exemple.demo.enumeration.TypeConge;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity

public class employee extends User {
	private static final long serialVersionUID=1L;
    private float soldeConge;
    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private List<DemandeConge> demandesConge;

	public employee() {
		
		
	}

	public employee( String nom, String prenom, String email, String password,float soldeConge) {
		super(nom, prenom, email, password);
		this.soldeConge=soldeConge;
	}
	public employee(int id) {
        this.id = id;
    }

	public float getSoldeConge() {
		return soldeConge;
	}

	public void setSoldeConge(float soldeConge) {
		this.soldeConge = soldeConge;
	}
	 public int getJoursDeConge(TypeConge typeConge) {
	        switch (typeConge) {
	            case ANNUEL:
	                return 30;  
	            case MALADIE:
	                return 15; 
	            case MATERNITE:
	                return 70;
	            case EXCEPTIONNEL:
	                return 5;   
	            case RTT:
	                return 4;  
	            case SANS_SOLDE:
	                return 0;   
	            default:
	                throw new IllegalArgumentException("Type de congé inconnu");
	        }
	    }
	 public boolean reduireSoldeConges(TypeConge typeConge) {
	        int joursDemandes = getJoursDeConge(typeConge);

	        if (soldeConge >= joursDemandes) {
	            soldeConge -= joursDemandes;
	            
	            return true;  
	        } else {
	            return false;  
	        }
	    }
	

}
