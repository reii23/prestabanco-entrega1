package com.prestabanco.managment.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "loan_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;  // name type

    @Column(nullable = false)
    private int maxTermYears;  // terms in years (max)

    @Column(nullable = false)
    private double minInterestRate;  // min interest rate

    @Column(nullable = false)
    private double maxInterestRate;  // max interest rate

    @Column(nullable = false)
    private double maxFinancingPercentage; 
}
