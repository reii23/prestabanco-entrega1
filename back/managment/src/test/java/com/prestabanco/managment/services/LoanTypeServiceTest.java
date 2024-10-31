package com.prestabanco.managment.services;

import com.prestabanco.managment.entities.LoanTypeEntity;
import com.prestabanco.managment.repositories.LoanTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoanTypeServiceTest {

    // mock the LoanTypeRepository
    @Mock
    private LoanTypeRepository loanTypeRepository;

    // inject the LoanTypeRepository into the LoanTypeService
    @InjectMocks
    private LoanTypeService loanTypeService;

    /**
     * TEST: Get all loan types
     * Test to verify find all loan types
     * This test verifies that the method `getAllLoanTypes` returns a list of loan types saved in the database
     * and that the list contains the expected loan types.
     */
    @Test
    void testGetAllLoanTypesWithSpecificTypes() {

        // create a list of loan types
        ArrayList<LoanTypeEntity> loanTypes = new ArrayList<>();
        loanTypes.add(new LoanTypeEntity(1L, "Primera Vivienda", 30, 3.5, 5.0, 80.0));
        loanTypes.add(new LoanTypeEntity(2L, "Segunda Vivienda", 20, 4.0, 6.0, 70.0));
        loanTypes.add(new LoanTypeEntity(3L, "Propiedades Comerciales", 25, 5.0, 7.0, 60.0));
        loanTypes.add(new LoanTypeEntity(4L, "Remodelación", 15, 4.5, 6.0, 50.0));

        // mock the findAll method of the LoanTypeRepository
        when(loanTypeRepository.findAll()).thenReturn(loanTypes);

        // call the method to be tested
        ArrayList<LoanTypeEntity> result = loanTypeService.getAllLoanTypes();

        // assert the expected result
        assertThat(result).isNotNull().hasSize(4);
        assertThat(result.get(0).getName()).isEqualTo("Primera Vivienda");
        assertThat(result.get(1).getName()).isEqualTo("Segunda Vivienda");
        assertThat(result.get(2).getName()).isEqualTo("Propiedades Comerciales");
        assertThat(result.get(3).getName()).isEqualTo("Remodelación");

        // verify that the findAll method was called
        verify(loanTypeRepository, times(1)).findAll();
    }

    /**
     * TEST: Save a specific loan type
     * Test to verify save a specific loan type in the database
     * This test verifies that the method `saveLoanType` saves a specific loan type in the database
     * and that the saved loan type is the expected loan type with the expected properties.
     */
    @Test
    void testSaveSpecificLoanType() {
        // create a loan type
        LoanTypeEntity loanType = new LoanTypeEntity(null, "Primera Vivienda", 30, 3.5, 5.0, 80.0);

        // mock the save method of the LoanTypeRepository
        when(loanTypeRepository.save(loanType)).thenReturn(new LoanTypeEntity(1L, "Primera Vivienda", 30, 3.5, 5.0, 80.0));

        // call the method to be tested
        LoanTypeEntity savedLoanType = loanTypeService.saveLoanType(loanType);

        // assert the expected result
        assertThat(savedLoanType).isNotNull();
        assertThat(savedLoanType.getName()).isEqualTo("Primera Vivienda");
        assertThat(savedLoanType.getMaxTermYears()).isEqualTo(30);
        assertThat(savedLoanType.getMinInterestRate()).isEqualTo(3.5);
        assertThat(savedLoanType.getMaxInterestRate()).isEqualTo(5.0);
        assertThat(savedLoanType.getMaxFinancingPercentage()).isEqualTo(80.0);

        // verify that the save method was called
        verify(loanTypeRepository, times(1)).save(loanType);
    }

    /**
     * TEST: Get a loan type by id
     * Test to verify find a loan type by id
     * This test verifies that the method `getLoanTypeById` returns a loan type by id
     * and that the loan type is the expected loan type with the expected properties.
     */
    @Test
    void testGetLoanTypeByIdWhenIdExists() {
        // create a loan type
        LoanTypeEntity loanType = new LoanTypeEntity(1L, "Primera Vivienda", 30, 3.5, 5.0, 80.0);

        // mock the findById method of the LoanTypeRepository
        when(loanTypeRepository.findById(1L)).thenReturn(Optional.of(loanType));

        // call the method to be tested
        Optional<LoanTypeEntity> result = loanTypeService.getLoanTypeById(1L);

        // assert the expected result
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Primera Vivienda");

        // verify that the findById method was called
        verify(loanTypeRepository, times(1)).findById(1L);
    }

    /**
     * TEST: Get a loan type by id
     * Test to verify find a loan type by id
     * This test verifies that the method `getLoanTypeById` returns an empty optional when the loan type id does not exist.
     */
    @Test
    void testGetLoanTypeByIdWhenIdDoesNotExist() {

        // mock the findById method of the LoanTypeRepository
        when(loanTypeRepository.findById(99L)).thenReturn(Optional.empty());

        // call the method to be tested
        Optional<LoanTypeEntity> result = loanTypeService.getLoanTypeById(99L);

        // assert the expected result
        assertThat(result).isNotPresent();

        // verify that the findById method was called
        verify(loanTypeRepository, times(1)).findById(99L);
    }

    /**
     * TEST: Get a loan type by name
     * Test to verify find a loan type by name
     * This test verifies that the method `getLoanTypeByName` returns a loan type by name
     * and that the loan type is the expected loan type with the expected properties.
     */
    @Test
    void testGetLoanTypeByNameWhenNameExists() {
        // create a loan type
        LoanTypeEntity loanType = new LoanTypeEntity(1L, "Primera Vivienda", 30, 3.5, 5.0, 80.0);

        // mock the findByName method of the LoanTypeRepository
        when(loanTypeRepository.findByName("Primera Vivienda")).thenReturn(loanType);

        // call the method to be tested
        LoanTypeEntity result = loanTypeService.getLoanTypeByName("Primera Vivienda");

        // assert the expected result
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Primera Vivienda");

        // verify that the findByName method was called
        verify(loanTypeRepository, times(1)).findByName("Primera Vivienda");
    }

    /**
     * TEST: Get a loan type by name
     * Test to verify find a loan type by name
     * This test verifies that the method `getLoanTypeByName` returns null when the loan type name does not exist.
     */
    @Test
    void testGetLoanTypeByNameWhenNameDoesNotExist() {

        // mock the findByName method of the LoanTypeRepository
        when(loanTypeRepository.findByName("Unknown Loan")).thenReturn(null);

        // call the method to be tested
        LoanTypeEntity result = loanTypeService.getLoanTypeByName("Unknown Loan");

        // assert the expected result
        assertThat(result).isNull();

        // verify that the findByName method was called
        verify(loanTypeRepository, times(1)).findByName("Unknown Loan");
    }

    /**
     * TEST: Delete a loan type by id
     * Test to verify delete a loan type by id
     * This test verifies that the method `deleteLoanTypeById` deletes a loan type by id
     * and that the loan type is successfully deleted.
     */
    @Test
    void testDeleteLoanTypeByIdSuccess() {

        // mock the deleteById method of the LoanTypeRepository
        doNothing().when(loanTypeRepository).deleteById(1L);

        // call the method to be tested
        boolean isDeleted = loanTypeService.deleteLoanTypeById(1L);

        // assert the expected result
        assertThat(isDeleted).isTrue();

        // verify that the deleteById method was called
        verify(loanTypeRepository, times(1)).deleteById(1L);
    }

    /**
     * TEST: Delete a loan type by id
     * Test to verify delete a loan type by id
     * This test verifies that the method `deleteLoanTypeById` does not delete a loan type by id
     * and that the loan type is not deleted.
     */
    @Test
    void testDeleteLoanTypeByIdFailure() {
        // mock the deleteById method of the LoanTypeRepository
        doThrow(new RuntimeException("Delete failed")).when(loanTypeRepository).deleteById(99L);

        // call the method to be tested
        boolean isDeleted = loanTypeService.deleteLoanTypeById(99L);

        // assert the expected result
        assertThat(isDeleted).isFalse();

        // verify that the deleteById method was called
        verify(loanTypeRepository, times(1)).deleteById(99L);
    }
}
