package com.prestabanco.managment.controllers;

import com.prestabanco.managment.entities.CreditRequestEntity;
import com.prestabanco.managment.entities.LoanTypeEntity;
import com.prestabanco.managment.repositories.LoanTypeRepository;
import com.prestabanco.managment.services.ClientService;
import com.prestabanco.managment.services.LoanTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/LoanType")
@CrossOrigin("*")
public class LoanTypeController {
    @Autowired
    LoanTypeService loanTypeService;

    // get all loan types
    @GetMapping("/")
    public ResponseEntity <ArrayList<LoanTypeEntity>> getAllLoanTypes() {
        ArrayList<LoanTypeEntity> loanTypes = loanTypeService.getAllLoanTypes();
        return ResponseEntity.ok(loanTypes);
    }

    // get a loan type by loan type id

    @GetMapping("/{id}")
    public ResponseEntity<Optional<LoanTypeEntity>> getLoanTypeById(@PathVariable Long id){
        Optional<LoanTypeEntity> loanType  = loanTypeService.getLoanTypeById(id);
        return ResponseEntity.ok(loanType);
    }

    @PostMapping("/")
    public ResponseEntity<LoanTypeEntity> saveLoanType(@RequestBody LoanTypeEntity loanType){
        LoanTypeEntity loanTypeNew = loanTypeService.saveLoanType(loanType);
        return ResponseEntity.ok(loanType);
    }

    // delete loan type by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteLoanTypeById(@PathVariable Long id) throws Exception {
        var isDeleted = loanTypeService.deleteLoanTypeById(id);
        return ResponseEntity.noContent().build();
    }

}