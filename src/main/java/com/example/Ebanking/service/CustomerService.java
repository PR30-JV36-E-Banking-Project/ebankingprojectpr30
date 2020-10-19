/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.CustomerEntity;
import com.example.Ebanking.entities.UserEntity;
import com.example.Ebanking.repository.CustomerRepositoryIF;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hoang Duy Nhat
 */
@Service
public class CustomerService implements CustomerServiceIF {

    @Autowired
    CustomerRepositoryIF customerRepositoryIF;

    @Override
    public void saveCustomer(CustomerEntity theCustomer) {
        customerRepositoryIF.save(theCustomer);
    }

    @Override
    public CustomerEntity loadCustomerByEmail(String email) throws UsernameNotFoundException {
        final Optional<CustomerEntity> optionalCumstomer = customerRepositoryIF.findByEmail(email);

        if (optionalCumstomer.isPresent()) {
            return optionalCumstomer.get();
        } else {
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
        }
    }


    @Override
    public CustomerEntity findByUserEntity(UserEntity userEntity) {
        return customerRepositoryIF.findByUserEntity(userEntity);
    }

    @Override
    public List<CustomerEntity> getCustomers() {
        return (List)customerRepositoryIF.findAll();
    }

}
