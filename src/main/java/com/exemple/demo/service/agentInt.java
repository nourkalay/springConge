package com.exemple.demo.service;

import java.util.List;

import com.example.demo.entity.Agent;


public interface agentInt {
	public List<Agent> getAllAgents();
	public Agent findAgent(int id);
	public boolean saveAgent(Agent agent);
	public boolean deleteAgent(int id);
	public Agent updateAgent(Agent agent,int id);

}
