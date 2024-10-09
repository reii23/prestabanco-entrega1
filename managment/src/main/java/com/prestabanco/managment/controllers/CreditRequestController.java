package com.prestabanco.managment.controllers;

import com.prestabanco.managment.entities.CreditRequestEntity;
import com.prestabanco.managment.services.CreditRequestService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/CreditRequest")
@CrossOrigin("*")
public class CreditRequestController {
    @Autowired
    CreditRequestService creditRequestService;

    // get all credit requests
    @GetMapping("/")
    public ResponseEntity<ArrayList<CreditRequestEntity>> getAllCreditRequests() {
        ArrayList<CreditRequestEntity> creditRequests = creditRequestService.getAllRequests();
        return ResponseEntity.ok(creditRequests); // ResponseEntity ok (creditRequests)
    }

    // get one request by the ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<CreditRequestEntity>> getCreditRequestById(@PathVariable Long id){
        Optional<CreditRequestEntity> creditRequest = creditRequestService.getRequestById(id);
        return ResponseEntity.ok(creditRequest);

    }

    // get requests by client ID
    @GetMapping("client/{clientId}")
    public ResponseEntity<List<CreditRequestEntity>> getCreditRequestByClientId(@PathVariable Long clientId){
        List<CreditRequestEntity> creditRequests = creditRequestService.getRequestByClientId(clientId);
        return ResponseEntity.ok(creditRequests);
    }

    //
}
