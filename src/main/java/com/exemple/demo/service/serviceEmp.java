package com.exemple.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.employee;
import com.exemple.demo.repository.emplyerep;

@Service
public class serviceEmp implements employeeInt{
	
	private emplyerep emplyrep;
	
	public serviceEmp(emplyerep emplyrep) {
		this.emplyrep=emplyrep;
	}

	@Override
	public List<employee> getAllEmployees() {
		return emplyrep.findAll();
	}

	@Override
	public employee findEmployee(int id) {
	    Optional<employee> employee = emplyrep.findById(id); 
	    return employee.orElse(null);  
	}

	@Override
	public boolean saveEmployee(employee employee) {
		if(emplyrep.save(employee)!=null)
	           
            return true;
       else
           return false;
       
	}

	@Override
	public boolean deleteEmployee(int id) {
		emplyrep.deleteById(id);
         return !emplyrep.existsById(id);
	}

	@Override
	public employee updateEmployee(employee employee, int id) {
		Optional<employee> existingEmployeeOpt = emplyrep.findById(id);
	    if (existingEmployeeOpt.isPresent()) {
	        employee existingEmployee = existingEmployeeOpt.get();
	        existingEmployee.setNom(employee.getNom());
	        existingEmployee.setPrenom(employee.getPrenom());
	        existingEmployee.setEmail(employee.getEmail());
	        existingEmployee.setPassword(employee.getPassword());
	        existingEmployee.setSoldeConge(employee.getSoldeConge());  
	        
	        return emplyrep.save(existingEmployee); 
	    } else {
	        return null;
	    }
	}
	
	@Override
	public Float consulterSoldeConge(int id) {
	    Optional<employee> entityOpt = emplyrep.findById(id);
	    if (entityOpt.isPresent()) {
	        return entityOpt.get().getSoldeConge(); 
	    } else {
	        return null;
	    }
	}
	

	
	

}
