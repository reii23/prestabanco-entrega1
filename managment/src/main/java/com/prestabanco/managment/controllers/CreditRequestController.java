package com.prestabanco.managment.controllers;

import com.prestabanco.managment.entities.CreditRequestEntity;
import com.prestabanco.managment.services.CreditRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
        return ResponseEntity.ok(creditRequests);
    }

    // get one request by the ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<CreditRequestEntity>> getCreditRequestById(@PathVariable Long id) {
        Optional<CreditRequestEntity> creditRequest = creditRequestService.getRequestById(id);
        return ResponseEntity.ok(creditRequest);
    }

    // get requests by client ID
    @GetMapping("client/{clientId}")
    public ResponseEntity<List<CreditRequestEntity>> getCreditRequestByClientId(@PathVariable Long clientId) {
        List<CreditRequestEntity> creditRequests = creditRequestService.getRequestByClientId(clientId);
        return ResponseEntity.ok(creditRequests);
    }

    // get all requests by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<CreditRequestEntity>> getCreditRequestByStatus(@PathVariable String status) {
        List<CreditRequestEntity> creditRequests = creditRequestService.getRequestByState(status);
        return ResponseEntity.ok(creditRequests);
    }

    // save a credit request
    @PostMapping("/")
    public ResponseEntity<CreditRequestEntity> saveCreditRequest(@RequestBody CreditRequestEntity creditRequest) {
        CreditRequestEntity savedRequest = creditRequestService.saveCreditRequest(creditRequest);
        return ResponseEntity.ok(savedRequest);
    }

    // Update a credit request
    @PutMapping("/{id}")
    public ResponseEntity<CreditRequestEntity> updateCreditRequest(@PathVariable Long id, @RequestBody CreditRequestEntity creditRequest) {
        Optional<CreditRequestEntity> existingRequest = creditRequestService.getRequestById(id);
        if (existingRequest.isPresent()) {
            creditRequest.setIdCreditRequest(id);
            CreditRequestEntity updatedRequest = creditRequestService.updateCreditRequest(creditRequest);
            return ResponseEntity.ok(updatedRequest);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a credit request by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditRequestById(@PathVariable Long id) throws Exception {
        boolean isDeleted = creditRequestService.deleteCreditRequestById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
