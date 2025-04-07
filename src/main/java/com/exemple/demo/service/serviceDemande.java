package com.exemple.demo.service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Agent;
import com.example.demo.entity.DemandeConge;
import com.exemple.demo.enumeration.Statut;
import com.exemple.demo.repository.demandeCongerep;
import com.exemple.demo.repository.emplyerep;


@Service
public class serviceDemande implements demandeCongeInt {
	
	private final emplyerep emplyrep; 
	private demandeCongerep demandecongerep;


	
	 private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	 
	 public serviceDemande(demandeCongerep demandecongerep, emplyerep emplyrep) {
		    this.demandecongerep = demandecongerep;
		    this.emplyrep = emplyrep;
		    
		}


	@Override
	public List<DemandeConge> getAllDemande() {
		return demandecongerep.findAll();
	}

	@Override
	public DemandeConge findDemande(int id) {
		Optional<DemandeConge> demandeConge = demandecongerep.findById(id); 
	    return demandeConge.orElse(null);  
	}

	@Override
	public boolean saveDemande(DemandeConge demandeConge) {
	    if (!isValidDates(demandeConge.getDateDebut(), demandeConge.getDateFin())) {
	        throw new IllegalArgumentException("La date de début ne peut pas être postérieure à la date de fin");
	    }

	   
	    LocalDate dateDebut = LocalDate.parse(demandeConge.getDateDebut(), formatter);
	    LocalDate today = LocalDate.now();

	    if (!today.isBefore(dateDebut.minusDays(9))) { 
	        throw new IllegalArgumentException("La demande doit être envoyée au moins 10 jours avant la date de début");
	    }

	    if (demandeConge.getStatut() == null || 
	        demandeConge.getStatut() == Statut.APPROUVE || 
	        demandeConge.getStatut() == Statut.REJETE) {
	        demandeConge.setStatut(Statut.EN_ATTENTE);
	    }

	    return demandecongerep.save(demandeConge) != null;
	}


	@Override
	public boolean deleteDemande(int id) {
		demandecongerep.deleteById(id);
        return !demandecongerep.existsById(id);
	}

	@Override
	public DemandeConge updateDemande(DemandeConge demandeConge, int id) {
		Optional<DemandeConge> existingCongeOpt = demandecongerep.findById(id);
	    if (existingCongeOpt.isPresent()) {
	    	DemandeConge existingConge = existingCongeOpt.get();
	        existingConge.setDateDebut(demandeConge.getDateDebut());
	        existingConge.setDateFin(demandeConge.getDateFin());
	        existingConge.setStatut(demandeConge.getStatut());
	        existingConge.setTypeConge(demandeConge.getTypeConge());

	        return demandecongerep.save(existingConge);
	    } else {
	        return null;
	    }
	}
	    
	    public boolean isValidDates(String dateDebut, String dateFin) {
	        try {
	            LocalDate debut = LocalDate.parse(dateDebut, formatter);
	            LocalDate fin = LocalDate.parse(dateFin, formatter);
	            return !fin.isBefore(debut);
	        } catch (DateTimeParseException e) {
	     
	            return false;
	        }
	    }
	    public DemandeConge approuverOuRejeterDemande(int id, boolean approuver,int agentId) {
	        DemandeConge demandeConge = demandecongerep.findById(id).orElse(null);
	        
	        Agent agent = demandeConge.getAgent();
	       

	        if (approuver) {
	            demandeConge.setStatut(Statut.APPROUVE);

	          
	            boolean soldeSuffisant = demandeConge.getEmploye().reduireSoldeConges(demandeConge.getTypeConge());

	            if (!soldeSuffisant) {
	                throw new IllegalArgumentException("Solde de congé insuffisant");
	            }
	            emplyrep.save(demandeConge.getEmploye());

	        } else {
	            demandeConge.setStatut(Statut.REJETE);
	        }

	        try {
	          
	            return demandecongerep.save(demandeConge);
	        } catch (Exception e) {
	            throw new RuntimeException("Erreur lors de la mise à jour de la demande de congé", e);
	        }
	    }
	    public List<DemandeConge> getDemandesByEmployeId(int employeId) {
	        return demandecongerep.findByEmployeId(employeId);
	    }



		
	

	



	
	
		
}
