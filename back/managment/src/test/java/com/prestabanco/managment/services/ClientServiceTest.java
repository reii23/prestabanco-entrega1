package com.prestabanco.managment.services;

import com.prestabanco.managment.entities.ClientEntity;
import com.prestabanco.managment.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock // mocking the ClientRepository
    private ClientRepository clientRepository;

    @InjectMocks // injecting the ClientRepository into the ClientService
    private ClientService clientService;

    /**
     * TEST: Get all clients
     * Test to verify find all clients
     * This test verifies that the method `getClients` returns a list of clients saved in the database
     * and that the list contains the expected clients.
     */
    @Test
    void testGetClients() {
        List<ClientEntity> mockClients = new ArrayList<>();
        mockClients.add(new ClientEntity(1L, "12345678-9", "Alan Turing", 41, 1000000L, "alan.turing@usach.cl"));
        mockClients.add(new ClientEntity(2L, "87654321-0", "Ada Lovelace", 36, 900000L, "ada.lovelace@usach.cl"));
        when(clientRepository.findAll()).thenReturn(mockClients);

        // call the method to be tested
        List<ClientEntity> clients = clientService.getClients();

        // assert the expected result
        assertThat(clients).isNotNull().hasSize(2); // check that the list is not null and has 2 clients (Alan Turing and Ada Lovelace)
        assertThat(clients.get(0).getName()).isEqualTo("Alan Turing");
        assertThat(clients.get(0).getRut()).isEqualTo("12345678-9");
        assertThat(clients.get(1).getName()).isEqualTo("Ada Lovelace");
        assertThat(clients.get(1).getRut()).isEqualTo("87654321-0");
    }

    /**
     * TEST: Save a client
     * Test to verify save a client in the database
     * This test verifies that the method `saveClient` saves a client in the database
     * and that the saved client is the expected client with the expected properties.
     */
    @Test
    void testSaveClient() {
        ClientEntity client = new ClientEntity(1L, "12345678-9", "Alan Turing", 41, 1000000L, "alan.turing@usach.cl");
        when(clientRepository.save(client)).thenReturn(client);

        // call the method to be tested
        ClientEntity savedClient = clientService.saveClient(client);

        // assert the expected result
        assertThat(savedClient).isNotNull();
        assertThat(savedClient.getName()).isEqualTo("Alan Turing");
        assertThat(savedClient.getRut()).isEqualTo("12345678-9");
        assertThat(savedClient.getAge()).isEqualTo(41);
        assertThat(savedClient.getSalary()).isEqualTo(1000000L);
        assertThat(savedClient.getEmail()).isEqualTo("alan.turing@usach.cl");
        verify(clientRepository, times(1)).save(client);
    }


    /**
     * TEST: Get a client by id
     * Test to verify find a client by id
     * This test verifies that the method `getClientById` returns a client by id
     * and that the client is the expected client with the expected properties.
     */
    @Test
    void testGetClientById() {
        // create a client
        ClientEntity client = new ClientEntity(1L, "12345678-9", "Alan Turing", 41, 1000000L, "alan.turing@usach.cl");

        // mock the clientRepository
        when(clientRepository.findById(1L)).thenReturn(java.util.Optional.of(client));

        // call the method to be tested
        ClientEntity foundClient = clientService.getClientById(1L);

        // assert the expected result
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getName()).isEqualTo("Alan Turing");
        assertThat(foundClient.getRut()).isEqualTo("12345678-9");
        assertThat(foundClient.getAge()).isEqualTo(41);
        assertThat(foundClient.getSalary()).isEqualTo(1000000L);
        assertThat(foundClient.getEmail()).isEqualTo("alan.turing@usach.cl");
    }


    /**
     * TEST: Get a client by Rut
     * Test to verify find a client by Rut
     * This test verifies that the method `getClientByRut` returns a client by Rut
     * and that the client is the expected client with the expected properties.
     */

    @Test
    void testGetClientByRut() {
        // create a client
        ClientEntity client = new ClientEntity(1L, "12345678-9", "Alan Turing", 41, 1000000L, "alan.turing@usach.cl");

        // mock the clientRepository
        when(clientRepository.findByRut("12345678-9")).thenReturn(client);

        // call the method to be tested
        ClientEntity foundClient = clientService.getClientByRut("12345678-9");

        // assert the expected result
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getName()).isEqualTo("Alan Turing");
        assertThat(foundClient.getRut()).isEqualTo("12345678-9");
        assertThat(foundClient.getAge()).isEqualTo(41);
        assertThat(foundClient.getSalary()).isEqualTo(1000000L);
        assertThat(foundClient.getEmail()).isEqualTo("alan.turing@usach.cl");
    }

    /**
     * TEST: Update a client
     * Test to verify update a client in the database
     * This test verifies that the method `updateClient` updates a client in the database
     */
    @Test
    void testUpdateClient() {
        // create a client
        ClientEntity client = new ClientEntity(1L, "12345678-9", "Alan Turing", 41, 1000000L, "alan.turing@usach.cl");

        // mock the clientRepository
        when(clientRepository.save(client)).thenReturn(client);


        // call the method to be tested
        ClientEntity updatedClient = clientService.updateClient(client);

        // assert the expected result
        assertThat(updatedClient).isNotNull();
        assertThat(updatedClient.getName()).isEqualTo("Alan Turing");
        assertThat(updatedClient.getRut()).isEqualTo("12345678-9");

    }

    /**
     * TEST: Delete a client
     * Test to verify delete a client in the database
     * This test verifies that the method `deleteClient` deletes a client in the database
     */
    @Test
    void testDeleteClient() {
        // simulate that the client exists
        when(clientRepository.existsById(1L)).thenReturn(true);
        // mock deleteById method
        doNothing().when(clientRepository).deleteById(1L);

        // call the method to be tested
        boolean isDeleted = clientService.deleteClient(1L);

        // assert the expected result
        assertThat(isDeleted).isTrue();
        // verify that deleteById has been called
        verify(clientRepository, times(1)).deleteById(1L);
    }


    /**
     * TEST: Delete a client Exception
     * Test to verify delete a client in the database
     * This test verifies that the method `deleteClient` throws an exception when deleting a client that does not exist
     */
    @Test
    void testDeleteClientException() {
        // simulate that the client does not exist
        when(clientRepository.existsById(2L)).thenReturn(false);

        // call the method to be tested
        RuntimeException exception = assertThrows(RuntimeException.class, () -> clientService.deleteClient(2L));
        assertThat(exception.getMessage()).isEqualTo("Client not found");

        // verify that deleteById has not been called
        verify(clientRepository, never()).deleteById(2L);
    }
}
