package com.prestabanco.managment.controllers;

import com.prestabanco.managment.entities.CreditSimulationEntity;
import com.prestabanco.managment.services.CreditSimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/CreditSimulation") // url CreditSimulationController
public class CreditSimulationController {
    @Autowired
    CreditSimulationService creditSimulationService;

    // endpoint to "post" something (data) in credit simulation
    @PostMapping
    public ResponseEntity<String> simulateCredit(@RequestBody CreditSimulationEntity creditSimulation) {
        creditSimulationService.saveSimulation(creditSimulation);

        double monthlyFee = creditSimulationService.calculateMonthlyFee(creditSimulation);

        return ResponseEntity.ok("Monthly fee is: $ " + monthlyFee);
    }



}
