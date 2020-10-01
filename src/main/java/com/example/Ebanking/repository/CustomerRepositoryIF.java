/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.repository;

import com.example.Ebanking.entities.Customer;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hoang Duy Nhat
 */
@Repository
public interface CustomerRepositoryIF extends CrudRepository<Customer, Integer>{
    Optional<Customer> findByEmail(String email);
}
