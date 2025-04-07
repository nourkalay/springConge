package com.example.demo;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.entity.DemandeConge;
import com.example.demo.entity.employee;
import com.exemple.demo.enumeration.Statut;
import com.exemple.demo.enumeration.TypeConge;

import jakarta.transaction.Transactional;

@SpringBootApplication
@Transactional
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.example.demo", "com.exemple.demo"})
@EnableJpaRepositories(basePackages = "com.exemple.demo.repository")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	

       
	}
	

}
