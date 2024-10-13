package com.prestabanco.managment.controllers;


import com.prestabanco.managment.entities.ClientEntity;
import com.prestabanco.managment.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// REMEMBER: CONTROLLER CONNECT WITH SERVICE LAYER

@RestController // json file
@RequestMapping("api/v1/clients")
@CrossOrigin("*")
public class ClientController {
    @Autowired // spring injection
    ClientService clientService;

    // get all clients
    @GetMapping("/")
    public ResponseEntity<List<ClientEntity>> listClients() {
    List<ClientEntity> clients = clientService.getClients();
        return ResponseEntity.ok(clients);
    }

    // get a client by id
    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getClientById(@PathVariable Long id) {
        ClientEntity client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }

    // HU2: NEW CLIENT REGISTER
    @PostMapping("/")
    public ResponseEntity<ClientEntity> saveClient(@RequestBody ClientEntity client) {
        ClientEntity clientNew = clientService.saveClient(client);
        return ResponseEntity.ok(clientNew);
    }

    // update a client
    @PutMapping("/")
    public ResponseEntity<ClientEntity> updateClient(@RequestBody ClientEntity client){
        ClientEntity clientUpdated = clientService.updateClient(client);
        return ResponseEntity.ok(clientUpdated);
    }

    // delete a client by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteClientById(@PathVariable Long id) throws Exception {
        var isDeleted = clientService.deleteClient(id);
        return ResponseEntity.noContent().build(); // ask
    }
}
