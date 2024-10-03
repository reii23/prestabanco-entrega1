package com.prestabanco.managment.services;

import ch.qos.logback.core.net.server.Client;
import com.prestabanco.managment.entities.ClientEntity;
import com.prestabanco.managment.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public ArrayList<ClientEntity> getClients(){
        return (ArrayList<ClientEntity>) clientRepository.findAll();
    }

    public ClientEntity saveClient(ClientEntity client){
        return clientRepository.save(client);
    }

    public ClientEntity getClientById(Long id){
        return clientRepository.findById(id).get();
    }

    public ClientEntity getClientByEmail(String email){
        return clientRepository.findByEmail(email);
    }

    public ClientEntity getClientByRut(String rut){
        return clientRepository.findByRut(rut);
    }

    public ClientEntity updateEmployee(ClientEntity client){
        return clientRepository.save(client);
    }

    public ClientEntity updateEmployee(ClientEntity client, Long id){
        return clientRepository.save(client);
    }

    public boolean deleteClient(Long id) throws Exception {
        try {
            clientRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
