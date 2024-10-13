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


    // DOCUMENTS: all documents for credit request in @Lob to work with bytes
    @Lob
    @Column(name = "income_proof_pdf")
    private byte[] incomeProofPdf;

    @Lob
    @Column(name = "property_valutaion_pdf")
    private byte[] propertyValuationPdf;

    @Lob
    @Column(name = "credit_history_pdf")
    private byte[] creditHistoryPdf;

    @Lob
    @Column(name = "first_propety_deed_pdf")
    private byte[] firstPropertyDeedPdf;

    @Lob
    @Column(name = "business_plan_pdf")
    private byte[] businessPlanPdf;

    @Lob
    @Column(name = "renovation_budget_pdf")
    private byte[] renovationBudget;

}