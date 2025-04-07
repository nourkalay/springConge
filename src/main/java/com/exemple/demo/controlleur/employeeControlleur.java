package com.exemple.demo.controlleur;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.DemandeConge;
import com.example.demo.entity.employee;

import com.exemple.demo.service.serviceDemande;
import com.exemple.demo.service.serviceEmp;

@RestController
@RequestMapping("/api/employees")
public class employeeControlleur {

    private final serviceEmp employeeService;
    private final serviceDemande serviceDemande;
    
    

    public employeeControlleur(serviceEmp employeeService,serviceDemande serviceDemande) {
        this.employeeService = employeeService;
        this.serviceDemande=serviceDemande;
    }

    @GetMapping
    public List<employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable int id) {
        employee emp = employeeService.findEmployee(id);
        if (emp != null) {
            return ResponseEntity.ok(emp);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employé avec l'ID " + id + " non trouvé.");
        }
    }

    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody employee employee) {
        boolean created = employeeService.saveEmployee(employee);
        if (created) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Employé ajouté avec succès !");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de l'ajout de l'employé.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@RequestBody employee employee, @PathVariable int id) {
        employee updated = employeeService.updateEmployee(employee, id);
        if (updated != null) {
            return ResponseEntity.ok("Employé mis à jour avec succès !");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Impossible de mettre à jour : employé introuvable.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        boolean deleted = employeeService.deleteEmployee(id);
        if (deleted) {
            return ResponseEntity.ok("Employé supprimé avec succès !");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Échec de la suppression : employé introuvable.");
        }
    }

    @GetMapping("/{id}/solde-conge")
    public ResponseEntity<?> getSoldeConge(@PathVariable int id) {
        Float solde = employeeService.consulterSoldeConge(id);
        if (solde != null) {
            return ResponseEntity.ok("Solde de congé : " + solde + " jours.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employé non trouvé pour consulter le solde de congé.");
        }
    }
    @GetMapping("/historique/{employeId}")
    public ResponseEntity<List<DemandeConge>> getHistoriqueDemandes(@PathVariable int employeId) {
        List<DemandeConge> demandes = serviceDemande.getDemandesByEmployeId(employeId);
        if (demandes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(demandes);
        }
        return ResponseEntity.ok(demandes);
    }

}
