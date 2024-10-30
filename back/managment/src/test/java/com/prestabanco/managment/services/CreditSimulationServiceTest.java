package com.prestabanco.managment.services;

import com.prestabanco.managment.entities.CreditSimulationEntity;
import com.prestabanco.managment.repositories.CreditSimulationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreditSimulationServiceTest {

    @Mock // mocking the CreditSimulationRepository
    private CreditSimulationRepository creditSimulationRepository;

    @InjectMocks // injecting the CreditSimulationRepository into the CreditSimulationService
    private CreditSimulationService creditSimulationService;

    /**
     * TEST: Save a simulation
     * Test to verify save a simulation in the database (CreditSimulationEntity)
     */
    @Test
    void testSaveSimulation() {
        CreditSimulationEntity simulation = new CreditSimulationEntity(1L, 100000000L, 20, 4.5f, 1);
        when(creditSimulationRepository.save(simulation)).thenReturn(simulation);

        // call the method to be tested
        CreditSimulationEntity savedSimulation = creditSimulationService.saveSimulation(simulation);

        // assert the expected result
        assertThat(savedSimulation).isNotNull();
        assertThat(savedSimulation.getLoanAmount()).isEqualTo(100000000L);
        assertThat(savedSimulation.getTermYears()).isEqualTo(20);
        assertThat(savedSimulation.getInterestRate()).isEqualTo(4.5f);

        // verify that the save method was called
        verify(creditSimulationRepository).save(simulation);
    }

    /**
     * TEST: Calculate monthly fee
     * Test to verify `calculateMonthlyFee` calculates the monthly fee correctly
     */
    @Test
    void testCalculateMonthlyFee() {
        CreditSimulationEntity simulation = new CreditSimulationEntity(1L, 100000000L, 20, 4.5f, 1);
        double expectedMonthlyFee = 632649.3762199708;

        // call the method to be tested
        double monthlyFee = creditSimulationService.calculateMonthlyFee(simulation);

        // assert the expected result
        assertThat(monthlyFee).isCloseTo(expectedMonthlyFee, within(0.01));
    }
}
