package com.exemple.demo.service;

import java.sql.Date;
import java.util.List;


import com.example.demo.entity.DemandeConge;

public interface demandeCongeInt {
	public List<DemandeConge> getAllDemande();
	public DemandeConge findDemande(int id);
	public boolean saveDemande(DemandeConge demandeConge);
	public boolean deleteDemande(int id);
	public DemandeConge updateDemande(DemandeConge demandeConge,int id);
	 public boolean isValidDates(String dateDebut, String dateFin);
	 public DemandeConge approuverOuRejeterDemande(int id, boolean approuver,int agentId);
}
