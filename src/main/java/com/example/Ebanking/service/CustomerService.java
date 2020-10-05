/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.Customer;
import com.example.Ebanking.repository.CustomerRepositoryIF;
import java.text.MessageFormat;
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
    public Customer getCustomerByUserName(String username) {
        Optional<Customer> cusOptional= customerRepositoryIF.findByUserName(username);
        return cusOptional.isPresent() ? cusOptional.get() : null;
    }

    @Override
    public void saveCustomer(Customer theCustomer) {
        customerRepositoryIF.save(theCustomer);
    }

    private CustomerRepositoryIF customerRepository;

    @Override
    public Customer loadCustomerByEmail(String email) throws UsernameNotFoundException {
        final Optional<Customer> optionalCumstomer = customerRepository.findByEmail(email);

        if (optionalCumstomer.isPresent()) {
            return optionalCumstomer.get();
        } else {
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
        }
    }

}
