package com.example.entity;

import java.sql.Date;

import com.exemple.enumeration.Statut;
import com.exemple.enumeration.TypeConge;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "demande")
public class DemandeConge {
	private static final long serialVersionUID=1L;
	  
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private Date dateDebut;
	    private Date dateFin;
	    @Enumerated(EnumType.STRING)
	    private Statut statut = Statut.EN_ATTENTE;
	    @Enumerated(EnumType.STRING)
	    private TypeConge typeConge;
	    
	    @ManyToOne 
	    @JoinColumn(name = "employe_id")
	    private employee employe;
	    
	    @ManyToOne
	    @JoinColumn(name = "agent_id")
	    private Agent agent;
	    
		public DemandeConge(Long id, Date dateDebut, Date dateFin, Statut statut, TypeConge typeConge) {
			this.id = id;
			this.dateDebut = dateDebut;
			this.dateFin = dateFin;
			this.statut = statut;
			this.typeConge = typeConge;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Date getDateDebut() {
			return dateDebut;
		}
		public void setDateDebut(Date dateDebut) {
			this.dateDebut = dateDebut;
		}
		public Date getDateFin() {
			return dateFin;
		}
		public void setDateFin(Date dateFin) {
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
		
		
	    
	    
}
