package com.example.jinhong_realestate.controller;

import com.example.jinhong_realestate.entity.Agent;
import com.example.jinhong_realestate.entity.Transaction;
import com.example.jinhong_realestate.service.AgentService;
import com.example.jinhong_realestate.service.RealEstateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agents")
public class AgentController {

    private final AgentService agentService;
    private final RealEstateService realEstateService;

    @GetMapping
    public List<Agent> getAllAgencies() {
        return agentService.findAllAgents();
    }

    @GetMapping("/{id}")
    public Agent getAgentById(@PathVariable("id") Long agentId) {
        return agentService.findAgentById(agentId);
    }

    @PostMapping
    public Agent createAgent(@RequestBody Agent Agent) {
        return agentService.saveAgent(Agent);
    }

    @PutMapping("/{id}")
    public Agent updateAgent(@PathVariable("id") Long agentId, @RequestBody Agent agent) {
        agent.setAgent_id(agentId);
        return agentService.saveAgent(agent);
    }

    @DeleteMapping("/{id}")
    public void deleteAgent(@PathVariable("id") Long agentId) {
        agentService.deleteAgent(agentId);
    }

    @GetMapping("/{agentId}/properties")
    public ResponseEntity<List<Transaction>> getPropertiesByAgent(@PathVariable Long agentId) {
        List<Transaction> properties = realEstateService.getPropertiesByAgent(agentId);
        return ResponseEntity.ok(properties);
    }
}
