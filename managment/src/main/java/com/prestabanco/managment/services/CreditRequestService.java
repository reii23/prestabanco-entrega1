package com.prestabanco.managment.services;

import com.prestabanco.managment.entities.CreditRequestEntity;
import com.prestabanco.managment.repositories.CreditRequestRepository;
import com.prestabanco.managment.repositories.CreditSimulationRepository;
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
    private CreditSimulationRepository creditSimulationRepository;

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

}
