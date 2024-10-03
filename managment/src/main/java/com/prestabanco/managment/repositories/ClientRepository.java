package com.prestabanco.managment.repositories;

import com.prestabanco.managment.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long>{
    ClientEntity findByRut(String rut);
    ClientEntity findByEmail(String email);
    ClientEntity findByAge(int age);
}


