package com.prestabanco.managment.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="credit_simulation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditSimulationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCreditSimulation; // id de la simulacion de credito
    private Long loanAmount; // monto prestamo deseado por el usuario
    private int termYears; // plazo
    private float interestRate; // tasa de interés
    private int loanType; // tipo de préstamo solicitado
}
