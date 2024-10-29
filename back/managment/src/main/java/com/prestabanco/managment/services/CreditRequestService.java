package com.prestabanco.managment.services;

import com.prestabanco.managment.entities.ClientEntity;
import com.prestabanco.managment.entities.CreditRequestEntity;
import com.prestabanco.managment.repositories.ClientRepository;
import com.prestabanco.managment.repositories.CreditRequestRepository;
import com.prestabanco.managment.repositories.CreditSimulationRepository;
import com.prestabanco.managment.repositories.LoanTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        return creditRequestRepository.findByLoanType(loanType);
    }

    // find list of credit request by state
    public List<CreditRequestEntity> getRequestByState(String status) {
        return creditRequestRepository.findByStatus(status);
    }

    // Save a new credit request
    public CreditRequestEntity saveCreditRequest(
            Long clientId, Long expenses, Long loanTypeId, String loanType, Double requestedAmount,
            int termYears, Double interestRate, String status, MultipartFile incomeProofPdf,
            MultipartFile propertyValuationPdf, MultipartFile creditHistoryPdf, MultipartFile renovationBudgetPdf,
            MultipartFile businessPlanPdf, MultipartFile firstPropertyDeedPdf, MultipartFile financialStateBusinessPdf
    ) throws IOException {
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setClientId(clientId);
        creditRequest.setExpenses(expenses);
        creditRequest.setLoanTypeId(loanTypeId);
        creditRequest.setLoanType(loanType);
        creditRequest.setRequestedAmount(requestedAmount);
        creditRequest.setTermYears(termYears);
        creditRequest.setInterestRate(interestRate);
        creditRequest.setStatus(status);

        if (incomeProofPdf != null && !incomeProofPdf.isEmpty()) {
            creditRequest.setIncomeProofPdf(incomeProofPdf.getBytes());
        }

        if (propertyValuationPdf != null && !propertyValuationPdf.isEmpty()) {
            creditRequest.setPropertyValuationPdf(propertyValuationPdf.getBytes());
        }

        if (creditHistoryPdf != null && !creditHistoryPdf.isEmpty()) {
            creditRequest.setCreditHistoryPdf(creditHistoryPdf.getBytes());
        }

        if (renovationBudgetPdf != null && !renovationBudgetPdf.isEmpty()) {
            creditRequest.setRenovationBudgetPdf(renovationBudgetPdf.getBytes());
        }

        if (businessPlanPdf != null && !businessPlanPdf.isEmpty()) {
            creditRequest.setBusinessPlanPdf(businessPlanPdf.getBytes());
        }

        if (firstPropertyDeedPdf != null && !firstPropertyDeedPdf.isEmpty()) {
            creditRequest.setFirstPropertyDeedPdf(firstPropertyDeedPdf.getBytes());
        }

        if (financialStateBusinessPdf != null && !financialStateBusinessPdf.isEmpty()) {
            creditRequest.setFinancialStateBusinessPdf(financialStateBusinessPdf.getBytes());
        }

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
    public String evaluateCreditRequest(Long id, CreditRequestEntity evaluationData) {
        CreditRequestEntity request = creditRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Credit request not found"));

        // boolean requirements evaluation
        request.setR1PaymentToIncome(evaluationData.getR1PaymentToIncome());
        request.setR2CreditHistory(evaluationData.getR2CreditHistory());
        request.setR3EmploymentStability(evaluationData.getR3EmploymentStability());
        request.setR4DebtToIncome(evaluationData.getR4DebtToIncome());
        request.setR5MaxFinancing(evaluationData.getR5MaxFinancing());
        request.setR6AgeRestriction(evaluationData.getR6AgeRestriction());

        // boolean requirements save capacity evaluation
        request.setR71MinimumBalance(evaluationData.getR71MinimumBalance());
        request.setR72ConsistentSavingsHistory(evaluationData.getR72ConsistentSavingsHistory());
        request.setR73PeriodicDeposits(evaluationData.getR73PeriodicDeposits());
        request.setR74BalanceYearsRatio(evaluationData.getR74BalanceYearsRatio());
        request.setR75RecentWithdrawals(evaluationData.getR75RecentWithdrawals());

        // evaluate requirement 7
        int r7Count = 0;
        if (Boolean.TRUE.equals(request.getR71MinimumBalance())) r7Count++;
        if (Boolean.TRUE.equals(request.getR72ConsistentSavingsHistory())) r7Count++;
        if (Boolean.TRUE.equals(request.getR73PeriodicDeposits())) r7Count++;
        if (Boolean.TRUE.equals(request.getR74BalanceYearsRatio())) r7Count++;
        if (Boolean.TRUE.equals(request.getR75RecentWithdrawals())) r7Count++;

        // determine the saving capacity (solid, moderate, insufficient)
        if (r7Count >= 5) {
            request.setR7SavingsCapacity("sólida");
        } else if (r7Count >= 3) {
            request.setR7SavingsCapacity("moderada");
        } else {
            request.setR7SavingsCapacity("insuficiente");
        }

        // evaluate approval or rejection of the credit request based on requirements and saving capacity
        if (Boolean.FALSE.equals(request.getR1PaymentToIncome()) ||
                Boolean.FALSE.equals(request.getR4DebtToIncome()) ||
                Boolean.FALSE.equals(request.getR6AgeRestriction())) {

            // REJECTED: payment to income ratio, debt to income ratio, or age restriction
            request.setStatus("rejected");
            creditRequestRepository.save(request);
            return "Solicitud rechazada por criterios obligatorios";
        }

        // PENDING: credit history, employment stability, or maximum financing
        if (Boolean.FALSE.equals(request.getR2CreditHistory()) ||
                Boolean.FALSE.equals(request.getR3EmploymentStability()) ||
                Boolean.FALSE.equals(request.getR5MaxFinancing())) {

            request.setStatus("pending review");
            creditRequestRepository.save(request);
            return "Solicitud pendiente de revisión adicional";
        }

        // REJECTED: saving capacity is insufficient
        if ("insuficiente".equals(request.getR7SavingsCapacity())) {
            request.setStatus("rejected");
            creditRequestRepository.save(request);
            return "Solicitud rechazada por capacidad de ahorro insuficiente";
        }

        // APPROVED: all requirements are met and saving capacity is solid or moderate
        request.setStatus("approved");
        creditRequestRepository.save(request);
        return "Solicitud aprobada";
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
        return true; // Simulate the credit history check
    }

    private boolean verifyEmploymentStability(CreditRequestEntity request) {
        ClientEntity client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        return client.getSalary() != null && client.getSalary() > 0;
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


    // get requests by RUT
    public List<CreditRequestEntity> getRequestsByClientRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        if (client != null) {
            return creditRequestRepository.findByClientId(client.getId());

        } else {
            throw new IllegalArgumentException("Client with RUT  "+ rut + " not found");
        }
    }
}
