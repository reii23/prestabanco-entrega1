package com.prestabanco.managment.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="credit-simulation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditSimulationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCreditSimulation; // id de la simulacion de credito
    private Long loanAmount; // monto prestamo
    private int term; // plazo
    private float interestRate; // tasa de interés




}
