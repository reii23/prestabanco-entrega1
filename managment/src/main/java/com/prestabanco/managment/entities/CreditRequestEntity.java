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
    private Long id;

    // save the PK Client (clientId) to complete the fields
    @Column(nullable = false)
    private Long clientId;

    // personalInfo client
    private Long expenses;

    // Loan information

    // TO DO: pensar si loanType debe ser una clase aparte o no

    private String loanType; // first, second dwelling, etc
    private String status; // loan approved, rejected, under review

    // Documents

}