package com.prestabanco.managment.repositories;

import com.prestabanco.managment.entities.LoanTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanTypeRepository extends CrudRepository<LoanTypeEntity, Long> {
    //find a loanType by name
    LoanTypeEntity findByName(String name);

}