package com.prestabanco.managment.services;

import com.prestabanco.managment.entities.CreditRequestEntity;
import com.prestabanco.managment.repositories.CreditRequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoanCostServiceTest {

    // mock the CreditRequestRepository
    @Mock
    private CreditRequestRepository creditRequestRepository;

    // inject the CreditRequestRepository into the LoanCostService
    @InjectMocks
    private LoanCostService loanCostService;

    /**
     * TEST: Calculate loan cost
     * Test to verify `calculateLoanCost` calculates the total loan cost correctly
     */
    @Test
    void testCalculateLoanCost() {
        // create a mock CreditRequestEntity
        CreditRequestEntity mockCreditRequest = new CreditRequestEntity();
        mockCreditRequest.setRequestedAmount(1000000.0);
        mockCreditRequest.setTermYears(15);
        mockCreditRequest.setInterestRate(5.0);

        // mock the findById method of the CreditRequestRepository
        when(creditRequestRepository.findById(1L)).thenReturn(java.util.Optional.of(mockCreditRequest));

        // call the method to be tested
        double totalLoanCost = loanCostService.calculateLoanCost(1L);

        // assert the expected result
        assertThat(totalLoanCost).isGreaterThan(0);
    }

    /**
     * TEST: Calculate loan cost with non-existent credit request
     * Test to verify that an IllegalArgumentException is thrown when trying to calculate the loan cost
     * for a non-existent credit request.
     */
    @Test
    void testCalculateLoanCostWithNonExistentCreditRequest() {
        // mock the findById method of the CreditRequestRepository
        when(creditRequestRepository.findById(2L)).thenReturn(java.util.Optional.empty());

        // call the method to be tested and assert that an IllegalArgumentException is thrown
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> loanCostService.calculateLoanCost(2L));
        assertThat(exception.getMessage()).isEqualTo("Credit request not found");
    }
}
