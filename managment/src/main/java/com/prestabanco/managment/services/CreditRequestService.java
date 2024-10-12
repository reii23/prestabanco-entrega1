package com.prestabanco.managment.services;

import com.prestabanco.managment.entities.ClientEntity;
import com.prestabanco.managment.entities.CreditRequestEntity;
import com.prestabanco.managment.repositories.ClientRepository;
import com.prestabanco.managment.repositories.CreditRequestRepository;
import com.prestabanco.managment.repositories.CreditSimulationRepository;
import com.prestabanco.managment.repositories.LoanTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreditRequestService {
    @Autowired
    CreditRequestRepository creditRequestRepository;
    @Autowired
    CreditSimulationRepository creditSimulationRepository;
    @Autowired
    LoanTypeRepository loanTypeRepository;
    @Autowired
    private ClientRepository clientRepository;


    // get all credit requests
    public ArrayList<CreditRequestEntity> getAllRequests() {
        return (ArrayList<CreditRequestEntity>) creditRequestRepository.findAll();
    }

    public Optional<CreditRequestEntity> getRequestById(Long id) {
        return creditRequestRepository.findById(id);
    }

    // get a credit request by clientId
    public List<CreditRequestEntity> getRequestByClientId(Long clientId) {
        return creditRequestRepository.findByClientId(clientId);
    }

    // find list of credit request by loanType
    public List<CreditRequestEntity> getRequestByLoanType(String loanType) {
        return creditRequestRepository.findByLoanType(loanType); // to do: se pedirá por clase o se guardará el loanTypeId?
    }

    // find list of credit request by state
    public List<CreditRequestEntity> getRequestByState(String status) {
        return creditRequestRepository.findByStatus(status);
    }

    // Save a new credit request
    public CreditRequestEntity saveCreditRequest(CreditRequestEntity creditRequest) {
        creditRequest.setStatus("in initial review");
        return creditRequestRepository.save(creditRequest);
    }

    // update a credit request
    public CreditRequestEntity updateCreditRequest(CreditRequestEntity creditRequest) {
        return creditRequestRepository.save(creditRequest);
    }

    // delete a credit request by id
    public boolean deleteCreditRequestById(Long id) {
        try {
            creditRequestRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // HU4: Evaluate Credit Request
    public String evaluateCreditRequest(Long id) {
        CreditRequestEntity request = creditRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Credit request not found"));

        // R1: Relation monthly fee/salary
        double cuotaIngreso = calculatePaymentToIncomeRatio(request);
        if (cuotaIngreso > 0.35) {
            request.setStatus("rejected");
            creditRequestRepository.save(request);
            return "Request rejected: Payment-to-income ratio exceeds 35%";
        }

        // R2: Credit History
        if (!verifyCreditHistory(request)) {
            request.setStatus("rejected");
            creditRequestRepository.save(request);
            return "Request rejected: Negative credit history";
        }

        // R3: Job Seniority
        if (!verifyEmploymentStability(request)) {
            request.setStatus("rejected");
            creditRequestRepository.save(request);
            return "Request rejected: Insufficient employment stability";
        }

        // R4: Relación debt/salary
        double deudaIngreso = calculateDebtToIncomeRatio(request);
        if (deudaIngreso > 0.50) {
            request.setStatus("rejected");
            creditRequestRepository.save(request);
            return "Request rejected: Debt-to-income ratio exceeds 50%";
        }

        // R5: Maxim month financy



        // R6: Age restriction
        if (!verifyAgeRestriction(request)) {
            request.setStatus("rejected");
            creditRequestRepository.save(request);
            return "Request rejected: Age restriction exceeded";
        }

        request.setStatus("approved");
        creditRequestRepository.save(request);
        return "Request approved";
    }

    private double calculatePaymentToIncomeRatio(CreditRequestEntity request) {
        ClientEntity client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        double monthlyPayment = calculateMonthlyPayment(request.getRequestedAmount(), request.getInterestRate(), request.getTermYears());
        return monthlyPayment / client.getSalary();
    }

    private double calculateDebtToIncomeRatio(CreditRequestEntity request) {
        ClientEntity client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        double totalDebt = request.getExpenses(); // Assuming expenses represent current debts
        double newLoanPayment = calculateMonthlyPayment(request.getRequestedAmount(), request.getInterestRate(), request.getTermYears());
        return (totalDebt + newLoanPayment) / client.getSalary();
    }

    private boolean verifyCreditHistory(CreditRequestEntity request) {
        // Simulate the credit history check
        return true; // In real scenarios, you would query an external credit history database (e.g., DICOM)
    }

    private boolean verifyEmploymentStability(CreditRequestEntity request) {
        ClientEntity client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        return client.getSalary() != null && client.getSalary() > 0;  // Simulate employment stability check
    }

    private boolean verifyAgeRestriction(CreditRequestEntity request) {
        ClientEntity client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        int loanEndAge = client.getAge() + request.getTermYears();
        return loanEndAge <= 75; // The loan must end before the client reaches 75 years of age
    }

    private double calculateMonthlyPayment(double loanAmount, double interestRate, int termYears) {
        double monthlyInterestRate = interestRate / 12 / 100;
        int totalPayments = termYears * 12;
        return (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -totalPayments));
    }
}