package com.prestabanco.managment.services;

import com.prestabanco.managment.entities.CreditRequestEntity;
import com.prestabanco.managment.repositories.CreditRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanCostService {

    @Autowired
    private CreditRequestRepository creditRequestRepository;

    public double calculateLoanCost(Long creditRequestId) {
        // obtain the credit request id, if not obtain, "credit rquest not found"
        CreditRequestEntity creditRequest = creditRequestRepository.findById(creditRequestId)
                .orElseThrow(() -> new IllegalArgumentException("Credit request not found"));

        // get credit request data (loanAmount, termYears and AnnualInterestRate)
        double loanAmount = creditRequest.getRequestedAmount();
        int termYears = creditRequest.getTermYears();
        double annualInterestRate = creditRequest.getInterestRate();

        // 1. calculate monthlyPay
        double monthlyInterestRate = (annualInterestRate / 12) / 100;
        int totalPayments = termYears * 12;
        double monthlyPayment = (loanAmount * monthlyInterestRate) /
                (1 - Math.pow(1 + monthlyInterestRate, -totalPayments));

        // 2. calculate insurance (fire,life)
        double lifeInsurance = loanAmount * 0.0003;
        double fireInsurance = 20000;

        // 3. calculate adminFee (1%)
        double adminFee = loanAmount * 0.01;

        // 4. calculate total monthly cost
        double totalMonthlyCost = monthlyPayment + lifeInsurance + fireInsurance;
        double totalLoanCost = (totalMonthlyCost * totalPayments) + adminFee;

        return totalLoanCost; // Solo devuelve el costo total
    }
}
