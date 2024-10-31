package com.prestabanco.managment.services;

import com.prestabanco.managment.entities.ClientEntity;
import com.prestabanco.managment.entities.CreditRequestEntity;
import com.prestabanco.managment.repositories.ClientRepository;
import com.prestabanco.managment.repositories.CreditRequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.withPrecision;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreditRequestServiceTest {

    // mock the CreditRequestRepository
    @Mock
    private CreditRequestRepository creditRequestRepository;

    // mock the ClientRepository
    @Mock
    private ClientRepository clientRepository;

    // inject the CreditRequestRepository into the CreditRequestService
    @InjectMocks
    private CreditRequestService creditRequestService;

    /**
     * TEST: Get all requests
     * Test to verify find all requests
     * This test verifies that the method `getAllRequests` returns a list of requests saved in the database
     * and that the list contains the expected requests.
     */
    @Test
    void testGetAllRequests() {
        // create a list of credit requests
        List<CreditRequestEntity> requests = new ArrayList<>();

        // add a credit request to the list
        requests.add(new CreditRequestEntity());

        // mock the findAll method of the CreditRequestRepository
        when(creditRequestRepository.findAll()).thenReturn(requests);

        // call the method to be tested
        List<CreditRequestEntity> result = creditRequestService.getAllRequests();

        // assert the expected result
        assertThat(result).isNotNull().hasSize(1);

        // verify that the findAll method was called
        verify(creditRequestRepository, times(1)).findAll();
    }

    /**
     * TEST: Get request by ID
     * Test to verify find request by ID
     * This test verifies that the method `getRequestById` returns a request with the specified ID
     * and that the request is the expected one.
     */
    @Test
    void testGetRequestById() {

        // create a credit request
        CreditRequestEntity request = new CreditRequestEntity();

        // mock the findById method of the CreditRequestRepository
        when(creditRequestRepository.findById(1L)).thenReturn(Optional.of(request));

        // call the method to be tested
        Optional<CreditRequestEntity> result = creditRequestService.getRequestById(1L);

        // assert the expected result
        assertThat(result).isPresent();

        // verify that the findById method was called
        verify(creditRequestRepository, times(1)).findById(1L);
    }

    /**
     * TEST: Get request by client ID
     * Test to verify find request by client ID
     * This test verifies that the method `getRequestByClientId` returns a list of requests with the specified client ID
     * and that the list contains the expected requests.
     */
    @Test
    void testGetRequestByClientId() {
        // create a list of credit requests
        List<CreditRequestEntity> requests = new ArrayList<>();

        // mock the findByClientId method of the CreditRequestRepository
        when(creditRequestRepository.findByClientId(1L)).thenReturn(requests);

        // call the method to be tested
        List<CreditRequestEntity> result = creditRequestService.getRequestByClientId(1L);

        // assert the expected result
        assertThat(result).isNotNull();

        // verify that the findByClientId method was called
        verify(creditRequestRepository, times(1)).findByClientId(1L);
    }

    /**
     * TEST: Get request by loan type
     * Test to verify find request by loan type
     * This test verifies that the method `getRequestByLoanType` returns a list of requests with the specified loan type
     * and that the list contains the expected requests.
     */
    @Test
    void testGetRequestByFirstHomeLoan() {
        // create a list of credit requests for "Primera Vivienda"
        List<CreditRequestEntity> requests = new ArrayList<>();
        requests.add(new CreditRequestEntity());

        // mock the findByLoanType method of the CreditRequestRepository
        when(creditRequestRepository.findByLoanType("Primera Vivienda")).thenReturn(requests);

        // call the method to be tested
        List<CreditRequestEntity> result = creditRequestService.getRequestByLoanType("Primera Vivienda");

        // assert the expected result
        assertThat(result).isNotNull().hasSize(1);

        // verify that the findByLoanType method was called
        verify(creditRequestRepository, times(1)).findByLoanType("Primera Vivienda");
    }

    /**
     * TEST: Get request by loan type
     * Test to verify find request by loan type
     * This test verifies that the method `getRequestByLoanType` returns a list of requests with the specified loan type
     * and that the list contains the expected requests.
     */
    @Test
    void testGetRequestBySecondHomeLoan() {
        // create a list of credit requests for "Segunda Vivienda"
        List<CreditRequestEntity> requests = new ArrayList<>();

        // add a credit request to the list
        requests.add(new CreditRequestEntity());

        // mock the findByLoanType method of the CreditRequestRepository
        when(creditRequestRepository.findByLoanType("Segunda Vivienda")).thenReturn(requests);

        // call the method to be tested
        List<CreditRequestEntity> result = creditRequestService.getRequestByLoanType("Segunda Vivienda");

        // assert the expected result
        assertThat(result).isNotNull().hasSize(1);

        // verify that the findByLoanType method was called
        verify(creditRequestRepository, times(1)).findByLoanType("Segunda Vivienda");
    }

    /**
     * TEST: Get request by loan type
     * Test to verify find request by loan type
     * This test verifies that the method `getRequestByLoanType` returns a list of requests with the specified loan type
     * and that the list contains the expected requests.
     */
    @Test
    void testGetRequestByCommercialPropertyLoan() {
        // create a list of credit requests for "Propiedades Comerciales"
        List<CreditRequestEntity> requests = new ArrayList<>();
        requests.add(new CreditRequestEntity());

        // mock the findByLoanType method of the CreditRequestRepository
        when(creditRequestRepository.findByLoanType("Propiedades Comerciales")).thenReturn(requests);

        // call the method to be tested
        List<CreditRequestEntity> result = creditRequestService.getRequestByLoanType("Propiedades Comerciales");

        // assert the expected result
        assertThat(result).isNotNull().hasSize(1);

        // verify that the findByLoanType method was called
        verify(creditRequestRepository, times(1)).findByLoanType("Propiedades Comerciales");
    }

    /**
     * TEST: Get request by loan type
     * Test to verify find request by loan type
     * This test verifies that the method `getRequestByLoanType` returns a list of requests with the specified loan type
     * and that the list contains the expected requests.
     */
    @Test
    void testGetRequestByRemodelationLoan() {
        List<CreditRequestEntity> requests = new ArrayList<>();
        requests.add(new CreditRequestEntity());

        when(creditRequestRepository.findByLoanType("Remodelación")).thenReturn(requests);

        List<CreditRequestEntity> result = creditRequestService.getRequestByLoanType("Remodelación");

        assertThat(result).isNotNull().hasSize(1);
        verify(creditRequestRepository, times(1)).findByLoanType("Remodelación");
    }

    /**
     * TEST: Get request by state
     * Test to verify find request by state
     * This test verifies that the method `getRequestByState` returns a list of requests with the specified state
     * and that the list contains the expected requests.
     */
    @Test
    void testGetRequestByState() {
        List<CreditRequestEntity> requests = new ArrayList<>();
        when(creditRequestRepository.findByStatus("approved")).thenReturn(requests);

        List<CreditRequestEntity> result = creditRequestService.getRequestByState("approved");

        assertThat(result).isNotNull();
        verify(creditRequestRepository, times(1)).findByStatus("approved");
    }

    /**
     * TEST: Save credit request
     * Test to verify save a credit request in the database
     * This test verifies that the method `saveCreditRequest` saves a credit request in the database
     * and that the saved credit request is the expected one with the expected properties.
     */
    @Test
    void testSaveCreditRequest() throws IOException {
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.getBytes()).thenReturn(new byte[]{1, 2, 3});

        CreditRequestEntity creditRequest = new CreditRequestEntity();
        when(creditRequestRepository.save(any(CreditRequestEntity.class))).thenReturn(creditRequest);

        CreditRequestEntity result = creditRequestService.saveCreditRequest(
                1L, 1000L, 1L, "Personal Loan", 50000.0, 10, 5.0, "under review",
                mockFile, mockFile, mockFile, mockFile, mockFile, mockFile, mockFile
        );

        assertThat(result).isNotNull();
        verify(creditRequestRepository, times(1)).save(any(CreditRequestEntity.class));
    }

    /**
     * TEST: Update credit request
     * Test to verify update a credit request in the database
     * This test verifies that the method `updateCreditRequest` updates a credit request in the database
     * and that the updated credit request is the expected one with the expected properties.
     */
    @Test
    void testUpdateCreditRequest() {
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        when(creditRequestRepository.save(creditRequest)).thenReturn(creditRequest);

        CreditRequestEntity result = creditRequestService.updateCreditRequest(creditRequest);

        assertThat(result).isNotNull();
        verify(creditRequestRepository, times(1)).save(creditRequest);
    }

    /**
     * TEST: Delete credit request by ID
     * Test to verify delete a credit request by ID
     * This test verifies that the method `deleteCreditRequestById` deletes a credit request by ID
     * and that the credit request is successfully deleted.
     */
    @Test
    void testDeleteCreditRequestByIdSuccess() {
        doNothing().when(creditRequestRepository).deleteById(1L);

        boolean result = creditRequestService.deleteCreditRequestById(1L);

        assertThat(result).isTrue();
        verify(creditRequestRepository, times(1)).deleteById(1L);
    }

    /**
     * TEST: Delete credit request by ID Failure
     * Test to verify delete a credit request by ID failure
     * This test verifies that the method `deleteCreditRequestById` does not delete a credit request by ID
     * and that the credit request is not deleted.
     */
    @Test
    void testDeleteCreditRequestByIdFailure() {
        doThrow(new RuntimeException()).when(creditRequestRepository).deleteById(1L);

        boolean result = creditRequestService.deleteCreditRequestById(1L);

        assertThat(result).isFalse();
        verify(creditRequestRepository, times(1)).deleteById(1L);
    }

    /**
     * TEST: Evaluate credit request
     * Test to verify evaluate a credit request
     * This test verifies that the method `evaluateCreditRequest` evaluates a credit request
     * and that the result is the expected one.
     */
    @Test
    void testEvaluateCreditRequestRejectedForMandatoryCriteria() {
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setR1PaymentToIncome(false);
        when(creditRequestRepository.findById(1L)).thenReturn(Optional.of(creditRequest));

        String result = creditRequestService.evaluateCreditRequest(1L, creditRequest);

        assertThat(result).isEqualTo("Solicitud rechazada por criterios obligatorios");
        verify(creditRequestRepository, times(1)).save(creditRequest);
    }

    /**
     * TEST: Evaluate credit request
     * Test to verify evaluate a credit request
     * This test verifies that the method `evaluateCreditRequest` evaluates a credit request
     */
    @Test
    void testEvaluateCreditRequestPendingReview() {
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setR1PaymentToIncome(true);
        creditRequest.setR4DebtToIncome(true);
        creditRequest.setR6AgeRestriction(true);
        creditRequest.setR2CreditHistory(false);
        when(creditRequestRepository.findById(1L)).thenReturn(Optional.of(creditRequest));

        String result = creditRequestService.evaluateCreditRequest(1L, creditRequest);

        assertThat(result).isEqualTo("Solicitud pendiente de revisión adicional");
        verify(creditRequestRepository, times(1)).save(creditRequest);
    }

    /**
     * TEST: Evaluate credit request
     * Test to verify evaluate a credit request
     * This test verifies that the method `evaluateCreditRequest` evaluates a credit request
     */
    @Test
    void testEvaluateCreditRequestRejectedForInsufficientSavings() {
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setR1PaymentToIncome(true);
        creditRequest.setR4DebtToIncome(true);
        creditRequest.setR6AgeRestriction(true);
        creditRequest.setR2CreditHistory(true);
        creditRequest.setR3EmploymentStability(true);
        creditRequest.setR5MaxFinancing(true);
        creditRequest.setR7SavingsCapacity("insuficiente");
        when(creditRequestRepository.findById(1L)).thenReturn(Optional.of(creditRequest));

        String result = creditRequestService.evaluateCreditRequest(1L, creditRequest);

        assertThat(result).isEqualTo("Solicitud rechazada por capacidad de ahorro insuficiente");
        verify(creditRequestRepository, times(1)).save(creditRequest);
    }

    /**
     * TEST: Evaluate credit request
     * Test to verify evaluate a credit request
     * This test verifies that the method `evaluateCreditRequest` evaluates a credit request
     */
    @Test
    void testEvaluateCreditRequestApproved() {
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setR1PaymentToIncome(true);
        creditRequest.setR4DebtToIncome(true);
        creditRequest.setR6AgeRestriction(true);
        creditRequest.setR2CreditHistory(true);
        creditRequest.setR3EmploymentStability(true);
        creditRequest.setR5MaxFinancing(true);

        // configure R7 criteria to approve the request
        creditRequest.setR71MinimumBalance(true);
        creditRequest.setR72ConsistentSavingsHistory(true);
        creditRequest.setR73PeriodicDeposits(true);
        creditRequest.setR74BalanceYearsRatio(true);
        creditRequest.setR75RecentWithdrawals(true);

        // mock the findById method of the CreditRequestRepository
        when(creditRequestRepository.findById(1L)).thenReturn(Optional.of(creditRequest));

        String result = creditRequestService.evaluateCreditRequest(1L, creditRequest);

        // assert the expected result
        assertThat(result).isEqualTo("Solicitud aprobada");
        verify(creditRequestRepository, times(1)).save(creditRequest);
    }

    /**
     * TEST: Get requests by client RUT
     * Test to verify find requests by client RUT
     */
    @Test
    void testGetRequestsByClientRutWhenClientExists() {
        when(clientRepository.findByRut("12345678")).thenReturn(
                new ClientEntity(1L, "12345678", "Alan Turing", 41, 50000L, "alan.turing@usach.cl")
        );

        when(creditRequestRepository.findByClientId(1L)).thenReturn(new ArrayList<>());

        List<CreditRequestEntity> result = creditRequestService.getRequestsByClientRut("12345678");

        assertThat(result).isNotNull();
        verify(creditRequestRepository, times(1)).findByClientId(1L);
    }

    /**
     * TEST: Get requests by client RUT when client does not exist
     * Test to verify find requests by client RUT
     */
    @Test
    void testGetRequestsByClientRutWhenClientDoesNotExist() {
        when(clientRepository.findByRut("12345678")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> creditRequestService.getRequestsByClientRut("12345678"));
    }

    /**
     * TEST: Save credit request with null files
     * Test to verify save a credit request with null files
     */
    @Test
    void testEvaluateCreditRequestRejectedForDebtToIncome() {
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setR1PaymentToIncome(true);
        creditRequest.setR4DebtToIncome(false); // Esta condición debería rechazar la solicitud
        creditRequest.setR6AgeRestriction(true);
        when(creditRequestRepository.findById(1L)).thenReturn(Optional.of(creditRequest));

        String result = creditRequestService.evaluateCreditRequest(1L, creditRequest);

        assertThat(result).isEqualTo("Solicitud rechazada por criterios obligatorios");
        verify(creditRequestRepository, times(1)).save(creditRequest);
    }

    /**
     * TEST: Evaluate credit request
     * Test to verify evaluate a credit request with pending review for employment stability
     */
    @Test
    void testEvaluateCreditRequestPendingReviewForEmploymentStability() {
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setR1PaymentToIncome(true);
        creditRequest.setR4DebtToIncome(true);
        creditRequest.setR6AgeRestriction(true);
        creditRequest.setR2CreditHistory(true);
        creditRequest.setR3EmploymentStability(false); // Esta condición debería resultar en una revisión adicional
        creditRequest.setR5MaxFinancing(true);
        when(creditRequestRepository.findById(1L)).thenReturn(Optional.of(creditRequest));

        String result = creditRequestService.evaluateCreditRequest(1L, creditRequest);

        assertThat(result).isEqualTo("Solicitud pendiente de revisión adicional");
        verify(creditRequestRepository, times(1)).save(creditRequest);
    }

    /**
     * TEST: Evaluate credit request
     * Test to verify evaluate a credit request with pending review for age restriction
     */
    @Test
    void testEvaluateCreditRequestWithModerateSavingsCapacity() {
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setR1PaymentToIncome(true);
        creditRequest.setR4DebtToIncome(true);
        creditRequest.setR6AgeRestriction(true);
        creditRequest.setR2CreditHistory(true);
        creditRequest.setR3EmploymentStability(true);
        creditRequest.setR5MaxFinancing(true);

        // configure R7 criteria to approve the request
        creditRequest.setR71MinimumBalance(true);
        creditRequest.setR72ConsistentSavingsHistory(true);
        creditRequest.setR73PeriodicDeposits(true);
        creditRequest.setR74BalanceYearsRatio(false);
        creditRequest.setR75RecentWithdrawals(false);

        when(creditRequestRepository.findById(1L)).thenReturn(Optional.of(creditRequest));

        String result = creditRequestService.evaluateCreditRequest(1L, creditRequest);

        assertThat(result).isEqualTo("Solicitud aprobada");
        verify(creditRequestRepository, times(1)).save(creditRequest);
    }

    /**
     * TEST: Evaluate credit request when client does not exist
     * Test to verify evaluate a credit request when client does not exist
     */
    @Test
    void testGetRequestsByClientRutThrowsExceptionWhenClientNotFound() {
        when(clientRepository.findByRut("12345678")).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            creditRequestService.getRequestsByClientRut("12345678");
        });

        assertThat(exception.getMessage()).isEqualTo("Client with RUT 12345678 not found");
    }

    /**
     * TEST: save credit request with null files
     * Test to verify save a credit request with null files
     * @throws IOException
     */
    @Test
    void testSaveCreditRequestWithNullFiles() throws IOException {
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        when(creditRequestRepository.save(any(CreditRequestEntity.class))).thenReturn(creditRequest);

        CreditRequestEntity result = creditRequestService.saveCreditRequest(
                1L, 1000L, 1L, "Personal Loan", 50000.0, 10, 5.0, "under review",
                null, null, null, null, null, null, null
        );

        assertThat(result).isNotNull();
        verify(creditRequestRepository, times(1)).save(any(CreditRequestEntity.class));
    }

    /**
     * TEST: delete credit request by ID with non-existent ID
     * Test to verify delete a credit request by ID with non-existent ID
     */
    @Test
    void testDeleteCreditRequestByIdWithNonExistentId() {
        doThrow(new RuntimeException("Not found")).when(creditRequestRepository).deleteById(99L);

        boolean result = creditRequestService.deleteCreditRequestById(99L);

        assertThat(result).isFalse();
        verify(creditRequestRepository, times(1)).deleteById(99L);
    }

    /**
     * TEST: test calculate payment to income ratio
     * Test to verify calculate payment to income ratio
     */
    @Test
    void testCalculatePaymentToIncomeRatio() {
        ClientEntity client = new ClientEntity(1L, "12345678", "Alan Turing", 41, 50000L, "alan.turing@usach.cl");
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setClientId(1L);
        creditRequest.setRequestedAmount(100000.0);
        creditRequest.setInterestRate(5.0);
        creditRequest.setTermYears(10);

        double ratio = creditRequestService.calculatePaymentToIncomeRatio(creditRequest);
        assertThat(ratio).isGreaterThan(0);
    }

    /**
     * TEST: calculate payment to income ratio when client does not exist
     * Test to verify calculate payment to income ratio when client does not exist
     */
    @Test
    void testCalculatePaymentToIncomeRatioThrowsExceptionWhenClientNotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setClientId(1L);

        assertThrows(IllegalArgumentException.class, () -> creditRequestService.calculatePaymentToIncomeRatio(creditRequest));
    }

    /**
     * TEST: calculate debt to income ratio
     * Test to verify calculate debt to income ratio
     */
    @Test
    void testCalculateDebtToIncomeRatio() {
        ClientEntity client = new ClientEntity(1L, "12345678", "Alan Turing", 41, 50000L, "alan.turing@usach.cl");
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setClientId(1L);
        creditRequest.setRequestedAmount(100000.0);
        creditRequest.setInterestRate(5.0);
        creditRequest.setTermYears(10);
        creditRequest.setExpenses(2000L);

        double ratio = creditRequestService.calculateDebtToIncomeRatio(creditRequest);
        assertThat(ratio).isGreaterThan(0);
    }

    /**
     * TEST: calculate debt to income ratio when client does not exist
     * Test to verify calculate debt to income ratio when client does not exist
     */
    @Test
    void testCalculateDebtToIncomeRatioThrowsExceptionWhenClientNotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setClientId(1L);

        assertThrows(IllegalArgumentException.class, () -> creditRequestService.calculateDebtToIncomeRatio(creditRequest));
    }

    /**
     * TEST: verify credit history
     * Test to verify credit history
     */
    @Test
    void testVerifyCreditHistory() {
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        boolean result = creditRequestService.verifyCreditHistory(creditRequest);
        assertThat(result).isTrue();
    }

    /**
     * TEST: verify employment stability with valid client
     * Test to verify employment stability with valid client
     */
    @Test
    void testVerifyEmploymentStabilityWithValidClient() {
        ClientEntity client = new ClientEntity(1L, "12345678", "Alan Turing", 41, 50000L, "alan.turing@usach.cl");
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setClientId(1L);

        boolean result = creditRequestService.verifyEmploymentStability(creditRequest);
        assertThat(result).isTrue();
    }

    /**
     * TEST: verify employment stability with no salary
     * Test to verify employment stability with no salary
     */
    @Test
    void testVerifyEmploymentStabilityWithNoSalary() {
        ClientEntity client = new ClientEntity(1L, "12345678", "Alan Turing", 41, null, "alan.turing@usach.cl");
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setClientId(1L);

        boolean result = creditRequestService.verifyEmploymentStability(creditRequest);
        assertThat(result).isFalse();
    }

    /**
     * TEST: verify employment stability with no client
     * Test to verify employment stability with no client
     */
    @Test
    void testVerifyEmploymentStabilityThrowsExceptionWhenClientNotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setClientId(1L);

        assertThrows(IllegalArgumentException.class, () -> creditRequestService.verifyEmploymentStability(creditRequest));
    }

    /**
     * TEST: verify age restriction with valid client
     * Test to verify age restriction with valid client
     */
    @Test
    void testVerifyAgeRestrictionValid() {
        ClientEntity client = new ClientEntity(1L, "12345678", "Alan Turing", 41, 50000L, "alan.turing@usach.cl");
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setClientId(1L);
        creditRequest.setTermYears(20);

        boolean result = creditRequestService.verifyAgeRestriction(creditRequest);
        assertThat(result).isTrue();
    }

    /**
     * TEST: verify age restriction with invalid client
     * Test to verify age restriction with invalid client
     */
    @Test
    void testVerifyAgeRestrictionInvalid() {
        ClientEntity client = new ClientEntity(1L, "12345678", "Alan Turing", 60, 50000L, "alan.turing@usach.cl");
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setClientId(1L);
        creditRequest.setTermYears(20);

        boolean result = creditRequestService.verifyAgeRestriction(creditRequest);
        assertThat(result).isFalse();
    }

    /**
     * TEST: verify age restriction with no client
     * Test to verify age restriction with no client
     */
    @Test
    void testVerifyAgeRestrictionThrowsExceptionWhenClientNotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setClientId(1L);

        assertThrows(IllegalArgumentException.class, () -> creditRequestService.verifyAgeRestriction(creditRequest));
    }

    /**
     * TEST: calculate monthly payment
     * Test to verify calculate monthly payment
     */
    @Test
    void testCalculateMonthlyPayment() {
        double monthlyPayment = creditRequestService.calculateMonthlyPayment(100000, 5.0, 10);
        assertThat(monthlyPayment).isGreaterThan(0);
    }

    /**
     * TEST: calculate monthly payment with zero interest
     * Test to verify calculate monthly payment with zero interest
     */
    @Test
    void testCalculateMonthlyPaymentWithZeroInterest() {
        double monthlyPayment = creditRequestService.calculateMonthlyPayment(100000, 0.0, 10);
        assertThat(monthlyPayment).isEqualTo(833.33, withPrecision(0.01)); // Expected payment for zero interest
    }

    /**
     * TEST: calculate monthly payment with short term
     * Test to verify calculate monthly payment with short term
     */
    @Test
    void testCalculateMonthlyPaymentWithShortTerm() {
        double monthlyPayment = creditRequestService.calculateMonthlyPayment(50000, 5.0, 1);
        assertThat(monthlyPayment).isGreaterThan(0);
    }

    /**
     * TEST: evaluate credit request rejected for age restriction
     * Test to verify evaluate a credit request rejected for age restriction
     */
    @Test
    void testEvaluateCreditRequestRejectedForAgeRestriction() {
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setR1PaymentToIncome(true);
        creditRequest.setR4DebtToIncome(true);
        creditRequest.setR6AgeRestriction(false); // Falla esta condición
        when(creditRequestRepository.findById(1L)).thenReturn(Optional.of(creditRequest));

        String result = creditRequestService.evaluateCreditRequest(1L, creditRequest);

        assertThat(result).isEqualTo("Solicitud rechazada por criterios obligatorios");
        verify(creditRequestRepository, times(1)).save(creditRequest);
    }

    /**
     * TEST: evaluate credit request pending review for credit history
     * Test to verify evaluate a credit request pending review for credit history
     */
    @Test
    void testEvaluateCreditRequestPendingReviewForCreditHistory() {
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setR1PaymentToIncome(true);
        creditRequest.setR4DebtToIncome(true);
        creditRequest.setR6AgeRestriction(true);
        creditRequest.setR2CreditHistory(false); // Falla esta condición
        creditRequest.setR3EmploymentStability(true);
        creditRequest.setR5MaxFinancing(true);
        when(creditRequestRepository.findById(1L)).thenReturn(Optional.of(creditRequest));

        String result = creditRequestService.evaluateCreditRequest(1L, creditRequest);

        assertThat(result).isEqualTo("Solicitud pendiente de revisión adicional");
        verify(creditRequestRepository, times(1)).save(creditRequest);
    }

    /**
     * TEST: evaluate credit request with insufficient savings capacity
     * Test to verify evaluate a credit request with insufficient savings capacity
     */
    @Test
    void testEvaluateCreditRequestWithInsufficientSavingsCapacity() {
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setR1PaymentToIncome(true);
        creditRequest.setR4DebtToIncome(true);
        creditRequest.setR6AgeRestriction(true);
        creditRequest.setR2CreditHistory(true);
        creditRequest.setR3EmploymentStability(true);
        creditRequest.setR5MaxFinancing(true);

        // Configura los criterios R7 para que la capacidad de ahorro sea insuficiente
        creditRequest.setR71MinimumBalance(false);
        creditRequest.setR72ConsistentSavingsHistory(false);
        creditRequest.setR73PeriodicDeposits(false);
        creditRequest.setR74BalanceYearsRatio(false);
        creditRequest.setR75RecentWithdrawals(false);

        when(creditRequestRepository.findById(1L)).thenReturn(Optional.of(creditRequest));

        String result = creditRequestService.evaluateCreditRequest(1L, creditRequest);

        assertThat(result).isEqualTo("Solicitud rechazada por capacidad de ahorro insuficiente");
        verify(creditRequestRepository, times(1)).save(creditRequest);
    }

    /**
     * TEST: calculate debt to income ratio with zero salary
     */
    @Test
    void testCalculateDebtToIncomeRatioWithZeroSalary() {
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setClientId(1L);
        creditRequest.setRequestedAmount(100000.0);
        creditRequest.setInterestRate(5.0);
        creditRequest.setTermYears(10);
        creditRequest.setExpenses(5000L);

        ClientEntity client = new ClientEntity();
        client.setSalary(0L); // Salario cero
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        assertThrows(ArithmeticException.class, () -> creditRequestService.calculateDebtToIncomeRatio(creditRequest));
    }

    /**
     * TEST: verify credit history with no credit history
     * Test to verify verify credit history with no credit history
     */
    @Test
    void testGetRequestsByClientRutWithNoCreditRequests() {
        ClientEntity client = new ClientEntity(1L, "12345678", "Alan Turing", 41, 50000L, "alan.turing@usach.cl");
        when(clientRepository.findByRut("12345678")).thenReturn(client);
        when(creditRequestRepository.findByClientId(1L)).thenReturn(new ArrayList<>()); // No hay solicitudes de crédito

        List<CreditRequestEntity> result = creditRequestService.getRequestsByClientRut("12345678");

        assertThat(result).isEmpty();
        verify(creditRequestRepository, times(1)).findByClientId(1L);
    }

    /**
     * TEST: verify credit history with credit history
     * Test to verify verify credit history with credit history
     */
    @Test
    void testSaveCreditRequestWithOneNonNullFile() throws IOException {
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.getBytes()).thenReturn(new byte[]{1, 2, 3});

        CreditRequestEntity creditRequest = new CreditRequestEntity();
        when(creditRequestRepository.save(any(CreditRequestEntity.class))).thenReturn(creditRequest);

        CreditRequestEntity result = creditRequestService.saveCreditRequest(
                1L, 1000L, 1L, "Personal Loan", 50000.0, 10, 5.0, "under review",
                mockFile, null, null, null, null, null, null // Solo un archivo no es nulo
        );

        assertThat(result).isNotNull();
        verify(creditRequestRepository, times(1)).save(any(CreditRequestEntity.class));
    }

    /**
     * TEST: evaluate credit request rejected for single mandatory failure
     * Test to verify evaluate a credit request rejected for single mandatory failure
     */
    @Test
    void testEvaluateCreditRequestRejectedForSingleMandatoryFailure() {
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setR1PaymentToIncome(true);
        creditRequest.setR4DebtToIncome(true);
        creditRequest.setR6AgeRestriction(false); // Falla esta condición
        creditRequest.setR2CreditHistory(true);
        creditRequest.setR3EmploymentStability(true);
        creditRequest.setR5MaxFinancing(true);

        when(creditRequestRepository.findById(1L)).thenReturn(Optional.of(creditRequest));

        String result = creditRequestService.evaluateCreditRequest(1L, creditRequest);

        assertThat(result).isEqualTo("Solicitud rechazada por criterios obligatorios");
        verify(creditRequestRepository, times(1)).save(creditRequest);
    }

    /*
    TEST: calculate monthly payment with zero loan amount
    Test to verify calculate monthly payment with zero loan amount
     */
    @Test
    void testCalculateMonthlyPaymentWithZeroLoanAmount() {
        double monthlyPayment = creditRequestService.calculateMonthlyPayment(0, 5.0, 10);
        assertThat(monthlyPayment).isEqualTo(0.0);
    }

    /**
     * TEST: calculate monthly payment with zero interest rate
     */
    @Test
    void testCalculateMonthlyPaymentWithZeroTermYears() {
        assertThrows(ArithmeticException.class, () -> creditRequestService.calculateMonthlyPayment(100000, 5.0, 0));
    }

    /**
     * TEST: verify employment stability with zero salary
     * Test to verify employment stability with zero salary
     */
    @Test
    void testVerifyEmploymentStabilityWithZeroSalary() {
        ClientEntity client = new ClientEntity(1L, "12345678", "Alan Turing", 41, 0L, "alan.turing@usach.cl");
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setClientId(1L);

        boolean result = creditRequestService.verifyEmploymentStability(creditRequest);
        assertThat(result).isFalse();
    }

    /**
     * TEST: evaluate credit request rejected for single optional failure
     * Test to verify evaluate a credit request rejected for single optional failure
     */
    @Test
    void testEvaluateCreditRequestPendingReviewForSingleOptionalFailure() {
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setR1PaymentToIncome(true);
        creditRequest.setR4DebtToIncome(true);
        creditRequest.setR6AgeRestriction(true);
        creditRequest.setR2CreditHistory(false); // Solo falla la revisión de historial crediticio
        creditRequest.setR3EmploymentStability(true);
        creditRequest.setR5MaxFinancing(true);

        when(creditRequestRepository.findById(1L)).thenReturn(Optional.of(creditRequest));

        String result = creditRequestService.evaluateCreditRequest(1L, creditRequest);

        assertThat(result).isEqualTo("Solicitud pendiente de revisión adicional");
        verify(creditRequestRepository, times(1)).save(creditRequest);
    }
}
