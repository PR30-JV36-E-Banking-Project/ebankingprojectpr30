/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.repository;

import com.example.Ebanking.entities.CustomerEntity;
import com.example.Ebanking.entities.UserEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author solid
 */
@Repository
public interface CustomerRepositoryIF extends  CrudRepository<CustomerEntity, Integer>{
    Optional<CustomerEntity> findByEmail(String email);
    CustomerEntity findByUserEntity(UserEntity userEntity);
    CustomerEntity findByEmailIgnoreCase(String email);
    CustomerEntity findByCustomerID(int customerID);
}
