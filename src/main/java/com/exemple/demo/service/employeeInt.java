package com.exemple.demo.service;

import java.util.List;

import com.example.demo.entity.employee;

public interface employeeInt {
	public List<employee> getAllEmployees();
	public employee findEmployee(int id);
	public boolean saveEmployee(employee employee);
	public boolean deleteEmployee(int id);
	public employee updateEmployee(employee employee,int id);
	public Float consulterSoldeConge(int id);

}
