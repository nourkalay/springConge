package com.exemple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.employee;

public interface EmployeeRepository extends JpaRepository<employee,Integer>{

}
