/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.repository;

import com.example.Ebanking.entities.CustomerEntity;
import com.example.Ebanking.entities.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author solid
 */
@Repository
public interface CustomerRepositoryIF extends CrudRepository<CustomerEntity, Integer> {

    Optional<CustomerEntity> findByEmail(String email);

    CustomerEntity findByUserEntity(UserEntity userEntity);

    CustomerEntity findByEmailIgnoreCase(String email);

    CustomerEntity findByCustomerID(int customerID);

    @Query("SELECT p FROM CustomerEntity p WHERE p.fullName LIKE %?1%"
            + "OR p.birthDay LIKE %?1%"
            + "OR p.sex LIKE %?1%"
            + "OR p.address LIKE %?1%"
            + "OR p.country LIKE %?1%"
            + "OR p.nationlaty LIKE %?1%"
            + "OR p.district LIKE %?1%"
            + "OR p.city LIKE %?1%"
            + "OR p.phone LIKE %?1%"
            + "OR p.email LIKE %?1%"
            + "OR p.IDcard LIKE %?1%")
    public List<CustomerEntity> search(String keyword);

    @Query("SELECT COUNT(customerID) FROM CustomerEntity")
    public int getNumberOfCustomers();
}
