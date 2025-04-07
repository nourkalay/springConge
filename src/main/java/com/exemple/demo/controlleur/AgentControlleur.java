package com.exemple.demo.controlleur;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Agent;
import com.exemple.demo.service.serviceAgent;

@RestController
@RequestMapping("/api/agents")
public class AgentControlleur {

    private final serviceAgent agentService;

    public AgentControlleur(serviceAgent agentService) {
        this.agentService = agentService;
    }

    @GetMapping
    public List<Agent> getAllAgents() {
        return agentService.getAllAgents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAgentById(@PathVariable int id) {
        Agent agent = agentService.findAgent(id);
        if (agent != null) {
            return ResponseEntity.ok(agent);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Agent avec l'ID " + id + " non trouvé.");
        }
    }

    @PostMapping
    public ResponseEntity<String> createAgent(@RequestBody Agent agent) {
        boolean created = agentService.saveAgent(agent);
        if (created) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Agent ajouté avec succès !");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de l'ajout de l'agent.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAgent(@RequestBody Agent agent, @PathVariable int id) {
        Agent updated = agentService.updateAgent(agent, id);
        if (updated != null) {
            return ResponseEntity.ok("Agent mis à jour avec succès !");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Impossible de mettre à jour : agent introuvable.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAgent(@PathVariable int id) {
        boolean deleted = agentService.deleteAgent(id);
        if (deleted) {
            return ResponseEntity.ok("Agent supprimé avec succès !");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Échec de la suppression : agent introuvable.");
        }
    }
}
