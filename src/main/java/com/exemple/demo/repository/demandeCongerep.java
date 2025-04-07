package com.exemple.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DemandeConge;

public interface demandeCongerep extends JpaRepository<DemandeConge,Integer> {
	 List<DemandeConge> findByEmployeId(int employeId);
}
