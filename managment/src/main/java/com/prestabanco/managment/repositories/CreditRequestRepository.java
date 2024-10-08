package com.prestabanco.managment.repositories;

import com.prestabanco.managment.entities.CreditRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditRequestRepository extends JpaRepository<CreditRequestEntity, Long> {

    // List all request by clientId
    // todas las solicitudes que realizó un cliente
    List<CreditRequestEntity> findByClientId(Long clientId);

    // List the request by loanTipe
    // todas las solicitudes según el tipo de préstamo
    List<CreditRequestEntity> findByLoanType(String loanType);

    // List the request by state (approved, rejected)
    List<CreditRequestEntity> findByStatus(String status);

}
