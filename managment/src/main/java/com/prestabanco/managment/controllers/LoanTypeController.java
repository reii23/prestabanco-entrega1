package com.prestabanco.managment.controllers;

import com.prestabanco.managment.entities.LoanTypeEntity;
import com.prestabanco.managment.services.LoanTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/LoanType")
@CrossOrigin("*")
public class LoanTypeController {
    @Autowired
    LoanTypeService loanTypeService;

   // TO DO: MAKE THE LOAN TYPE CONTROLLERS AND TEST THE BACKEND W/ POSTGRES (HU3)

}
