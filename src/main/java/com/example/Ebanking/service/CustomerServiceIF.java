/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.Customer;

/**
 *
 * @author Hoang Duy Nhat
 */

public interface CustomerServiceIF {
    public void saveCustomer(Customer theCustomer);
    public Customer loadCustomerByUsername(String email);
}
