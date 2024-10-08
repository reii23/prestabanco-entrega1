package com.prestabanco.managment.services;


import com.prestabanco.managment.entities.LoanTypeEntity;
import com.prestabanco.managment.repositories.LoanTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class LoanTypeService {
    @Autowired
    LoanTypeRepository loanTypeRepository;

    // get all loan types
    public ArrayList<LoanTypeEntity> getAllLoanTypes() {
        return (ArrayList<LoanTypeEntity>) loanTypeRepository.findAll();
    }

    // get a loan type by loan type id
    public Optional<LoanTypeEntity> getLoanTypeById(Long id) {
        return loanTypeRepository.findById(id);
    }

    // save a loan type
    public LoanTypeEntity saveLoanType(LoanTypeEntity loanType) {
        return loanTypeRepository.save(loanType);
    }

    // get a loan type by name
    public LoanTypeEntity getLoanTypeByName(String name) {
        return loanTypeRepository.findByName(name);
    }

    // delete a loan type by id
    public boolean deleteLoanType(Long id) {
        try {
            loanTypeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

