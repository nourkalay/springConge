package com.exemple.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Agent;
import com.example.demo.entity.employee;
import com.exemple.demo.repository.agentrep;

@Service
public class serviceAgent implements agentInt {
	
	private agentrep agentrep;
	
	

	public serviceAgent(agentrep agentrep) {
		this.agentrep = agentrep;
	}

	@Override
	public List<Agent> getAllAgents() {
		return agentrep.findAll();
	}

	@Override
	public Agent findAgent(int id) {
		 Optional<Agent> agent = agentrep.findById(id); 
		    return agent.orElse(null);  
		
	}

	@Override
	public boolean saveAgent(Agent agent) {
		if(agentrep.save(agent)!=null)
	           
            return true;
       else
           return false;
       
	}

	@Override
	public boolean deleteAgent(int id) {
		agentrep.deleteById(id);
        return !agentrep.existsById(id);
	}

	@Override
	public Agent updateAgent(Agent agent, int id) {
		Optional<Agent> existingAgentOpt = agentrep.findById(id);
	    if (existingAgentOpt.isPresent()) {
	    	Agent existingAgent = existingAgentOpt.get();
	    	existingAgent.setNom(agent.getNom());
	    	existingAgent.setPrenom(agent.getPrenom());
	    	existingAgent.setEmail(agent.getEmail());
	    	existingAgent.setPassword(agent.getPassword());
	        
	        return agentrep.save(existingAgent); 
	    } else {
	        return null;
	    }
	}
	

}
