/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.CustomerEntity;
import com.example.Ebanking.entities.UserEntity;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Hoang Duy Nhat
 */

public interface CustomerServiceIF {
    public void saveCustomer(CustomerEntity theCustomer);
    public CustomerEntity loadCustomerByEmail(String email);
    public CustomerEntity findByUserEntity(UserEntity userEntity);
    public List <CustomerEntity> getCustomers(String keyword);
}
