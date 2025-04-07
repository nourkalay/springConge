package com.example.demo.entity;



import com.exemple.demo.enumeration.Statut;
import com.exemple.demo.enumeration.TypeConge;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "demande")
public class DemandeConge {
	private static final long serialVersionUID=1L;
	  
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
		@Column(length = 10)
		 @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La dateDebut doit être au format yyyy-MM-dd")
	    private String dateDebut;
		@Column(length = 10)
		 @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La dateDebut doit être au format yyyy-MM-dd")
	    private String dateFin;
	    @Enumerated(EnumType.STRING)
	    private Statut statut = Statut.EN_ATTENTE;
	    @Column(length = 15)
	    @Enumerated(EnumType.STRING)
	    private TypeConge typeConge;
	    
	    @ManyToOne 
	    @JoinColumn(name = "employe_id")
	    private employee employe;
	    
	    @ManyToOne
	    @JoinColumn(name = "agent_id")
	    private Agent agent;
	    
		public DemandeConge(int id, String dateDebut, String dateFin, Statut statut, TypeConge typeConge,employee employe ) {
			this.id = id;
			this.dateDebut = dateDebut;
			this.dateFin = dateFin;
			this.statut = statut;
			this.typeConge = typeConge;
			this.employe=employe;
			
		}
		public DemandeConge() {
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDateDebut() {
			return dateDebut;
		}
		public void setDateDebut(String dateDebut) {
			this.dateDebut = dateDebut;
		}
		public String getDateFin() {
			return dateFin;
		}
		public void setDateFin(String dateFin) {
			this.dateFin = dateFin;
		}
		public Statut getStatut() {
			return statut;
		}
		public void setStatut(Statut statut) {
			this.statut = statut;
		}
		public TypeConge getTypeConge() {
			return typeConge;
		}
		public void setTypeConge(TypeConge typeConge) {
			this.typeConge = typeConge;
		}
		public employee getEmploye() {
	        return employe;
	    }

	    public void setEmploye(employee employe) {
	        this.employe = employe;
	    }
		public Agent getAgent() {
			return agent;
		}
		public void setAgent(Agent agent) {
			this.agent = agent;
		}
		
	    
	    
}
