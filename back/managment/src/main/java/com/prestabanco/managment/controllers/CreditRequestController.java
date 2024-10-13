package com.prestabanco.managment.controllers;

import com.prestabanco.managment.entities.CreditRequestEntity;
import com.prestabanco.managment.services.CreditRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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
    public ResponseEntity<CreditRequestEntity> saveCreditRequest(
            @RequestParam("clientId") Long clientId,
            @RequestParam("expenses") Long expenses,
            @RequestParam("loanTypeId") Long loanTypeId,
            @RequestParam("loanType") String loanType,
            @RequestParam("requestedAmount") Double requestedAmount,
            @RequestParam("termYears") int termYears,
            @RequestParam("interestRate") Double interestRate,
            @RequestParam("status") String status,
            @RequestParam("incomeProofPdf") MultipartFile incomeProofPdf,
            @RequestParam("propertyValuationPdf") MultipartFile propertyValuationPdf,
            @RequestParam("creditHistoryPdf") MultipartFile creditHistoryPdf) throws IOException {

        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setClientId(clientId);
        creditRequest.setExpenses(expenses);
        creditRequest.setLoanTypeId(loanTypeId);
        creditRequest.setLoanType(loanType);
        creditRequest.setRequestedAmount(requestedAmount);
        creditRequest.setTermYears(termYears);
        creditRequest.setInterestRate(interestRate);
        creditRequest.setStatus(status);
        creditRequest.setIncomeProofPdf(incomeProofPdf.getBytes());  // Guardar el archivo como byte[]
        creditRequest.setPropertyValuationPdf(propertyValuationPdf.getBytes());
        creditRequest.setCreditHistoryPdf(creditHistoryPdf.getBytes());

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

    // get pdf income proof in credit request
    @GetMapping("/{id}/incomeProofPdf")
    public ResponseEntity<byte[]> getIncomeProofPdf(@PathVariable Long id) {
        Optional<CreditRequestEntity> creditRequest = creditRequestService.getRequestById(id);

        if (creditRequest.isPresent() && creditRequest.get().getIncomeProofPdf() != null) {
            byte[] pdfBytes = creditRequest.get().getIncomeProofPdf();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "incomeProof.pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // get pdf property valuation in credit request
    @GetMapping("/{id}/propertyValuationPdf")
    public ResponseEntity<byte[]> getPropertyValuationPdf(@PathVariable Long id) {
        Optional<CreditRequestEntity> creditRequest = creditRequestService.getRequestById(id);
        if (creditRequest.isPresent() && creditRequest.get().getPropertyValuationPdf() != null) {
            byte[] pdfBytes = creditRequest.get().getPropertyValuationPdf();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "propertyValuation.pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // get credit history pdf in credit request
    @GetMapping("/{id}/creditHistoryPdf")
    public ResponseEntity<byte[]> getCreditHistoryPdf(@PathVariable Long id) {
        Optional<CreditRequestEntity> creditRequest = creditRequestService.getRequestById(id);

        if (creditRequest.isPresent() && creditRequest.get().getCreditHistoryPdf() != null) {
            byte[] pdfBytes = creditRequest.get().getCreditHistoryPdf();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "creditHistory.pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // get first property deed pdf in credit request
    @GetMapping("/{id}/firstPropertyDeedPdf")
    public ResponseEntity<byte[]> getFirstPropertyDeedPdf(@PathVariable Long id) {
        Optional<CreditRequestEntity> creditRequest = creditRequestService.getRequestById(id);
        if (creditRequest.isPresent() && creditRequest.get().getFirstPropertyDeedPdf() != null) {
            byte[] pdfBytes = creditRequest.get().getFirstPropertyDeedPdf();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "creditHistory.pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // get first property deed pdf in credit request
    @GetMapping("/{id}/businessPlanPdf")
    public ResponseEntity<byte[]> getBusinessPlanPdf(@PathVariable Long id) {
        Optional<CreditRequestEntity> creditRequest = creditRequestService.getRequestById(id);
        if (creditRequest.isPresent() && creditRequest.get().getBusinessPlanPdf() != null) {
            byte[] pdfBytes = creditRequest.get().getBusinessPlanPdf();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachement", "businessPlan.pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // get renovation budget pdf in credit request
    @GetMapping("/{id}/renovationBudgetPdf")
    public ResponseEntity<byte[]> getRenovationBudgetPdf(@PathVariable Long id) {
        Optional<CreditRequestEntity> creditRequest = creditRequestService.getRequestById(id);
        if (creditRequest.isPresent() && creditRequest.get().getRenovationBudget() != null) {
            byte[] pdfBytes = creditRequest.get().getBusinessPlanPdf();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachement", "businessPlan.pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // HU4: evaluate a credit request by executive
    @PostMapping("/evaluate/{id}")
    public ResponseEntity<String> evaluateCreditRequest(@PathVariable Long id) {
        try {
            String evaluationResult = creditRequestService.evaluateCreditRequest(id);
            return ResponseEntity.ok(evaluationResult);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // HU5: GET STATUS CREDIT REQUEST
    @GetMapping("/{id}/status")
    public ResponseEntity<String> getCreditRequestStatus(@PathVariable Long id) {
        Optional<CreditRequestEntity> creditRequest = creditRequestService.getRequestById(id);
        if (creditRequest.isPresent()) {
            String status = creditRequest.get().getStatus();
            return ResponseEntity.ok(status);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Credit request not found");
        }
    }
}

