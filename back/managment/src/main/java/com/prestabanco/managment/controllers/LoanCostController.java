package com.prestabanco.managment.controllers;

import com.prestabanco.managment.services.LoanCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loanCost")
@CrossOrigin("*")
public class LoanCostController {

    @Autowired
    private LoanCostService loanCostService;
    
    @GetMapping("/calculate/{creditRequestId}")
    public ResponseEntity<Double> calculateLoanCost(@PathVariable Long creditRequestId) {
        double loanCost = loanCostService.calculateLoanCost(creditRequestId);
        return ResponseEntity.ok(loanCost);
    }
}
