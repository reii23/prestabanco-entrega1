package com.prestabanco.managment.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "credit_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idCreditRequest;

    // save the PK Client (clientId) to complete the fields
    @Column(nullable = false)
    private Long clientId;

    // personalInfo client
    private Long expenses;


    // id type (first property, second property)
    @Column(nullable = false)
    private Long loanTypeId;

    // Loan information
    private String loanType; // first, second dwelling, etc
    private Double requestedAmount;
    private int termYears;
    private Double interestRate;

    // Loan status
    private String status; // loan approved, rejected, under review


    // TO DO: PENSAR DE QUE FORMA SE VAN A INGRESAR LOS ARCHIVOS PDF (SI EN LA MISMA SOLICITUD O SE CREARÁ UNA CLASE CLIENTDOCUMENTS)
    //
    // Loan Type Requirements (ingresos, avaluo, historial crediticio)
    // private boolean incomeProof;
    // private boolean propertyValuation;
    // private boolean creditHistory;

    // more requirements (loan type)
    // private boolean firstPropertyDeed; // first property (only loantype second property)
    // private boolean businessPlan; // business plan (only loantype comercial property)
    // private boolean renovationBudget; // presupuesto (only loan type remodelation)

}