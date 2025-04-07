package com.exemple.demo.controlleur;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.DemandeConge;
import com.exemple.demo.service.serviceDemande;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/demandes")
public class DemandeCongeControlleur {

    private final serviceDemande demandeService;

    public DemandeCongeControlleur(serviceDemande demandeService) {
        this.demandeService = demandeService;
    }

    @GetMapping
    public List<DemandeConge> getAllDemandes() {
        return demandeService.getAllDemande();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDemandeById(@PathVariable int id) {
        DemandeConge demande = demandeService.findDemande(id);
        if (demande == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Demande non trouvée avec l'id: " + id);
        }
        return ResponseEntity.ok(demande);
    }

    @PostMapping
    public ResponseEntity<String> createDemande(@Valid @RequestBody DemandeConge demandeConge) {
        try {
            boolean saved = demandeService.saveDemande(demandeConge);
            if (saved) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Demande de congé créée avec succès");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la création de la demande");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDemande(@RequestBody DemandeConge demandeConge, @PathVariable int id) {
        DemandeConge updated = demandeService.updateDemande(demandeConge, id);
        if (updated != null) {
            return ResponseEntity.ok("Demande de congé mise à jour avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Demande introuvable pour l'id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDemande(@PathVariable int id) {
        demandeService.deleteDemande(id);
        return ResponseEntity.ok("Demande de congé annulée avec succès");
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> approuverOuRejeterDemande(
            @PathVariable int id,
            @RequestParam boolean approuver,
            @RequestParam int agentId) {
        try {
            demandeService.approuverOuRejeterDemande(id, approuver, agentId);
            String message = approuver ? "Demande approuvée avec succès" : "Demande rejetée avec succès";
            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur : " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur");
        }
    }
    @GetMapping("/{id}/historique-demandes")
    public List<DemandeConge> getHistoriqueDemandes(@PathVariable int id) {
        return demandeService.getDemandesByEmployeId(id);
    }

}
