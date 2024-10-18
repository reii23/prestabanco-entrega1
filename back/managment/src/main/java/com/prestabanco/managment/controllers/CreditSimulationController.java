package com.prestabanco.managment.controllers;

import com.prestabanco.managment.entities.CreditSimulationEntity;
import com.prestabanco.managment.services.CreditSimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/CreditSimulation") // url CreditSimulationController
@CrossOrigin("*")
public class CreditSimulationController {
    @Autowired
    CreditSimulationService creditSimulationService;

    // H1: CREDIT SIMULATION
    @PostMapping("/")
    public ResponseEntity<String> simulateCredit(@RequestBody CreditSimulationEntity creditSimulation) {
        creditSimulationService.saveSimulation(creditSimulation);
        double monthlyFee = creditSimulationService.calculateMonthlyFee(creditSimulation);
        return ResponseEntity.ok(String.valueOf(monthlyFee));
    }
}
