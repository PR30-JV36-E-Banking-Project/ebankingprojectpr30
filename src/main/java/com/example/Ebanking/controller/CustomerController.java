/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.controller;

import com.example.Ebanking.entities.CustomerEntity;
import com.example.Ebanking.service.CustomerServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author solid
 */
@Controller
@RequestMapping("account")
public class CustomerController {

    @Autowired
    private CustomerServiceIF customerService;

    @RequestMapping(value = "showForm", method = RequestMethod.GET)
    public String showFormForAdd(Model theModel) {
        CustomerEntity theCustomer = new CustomerEntity();
        theModel.addAttribute("customer", theCustomer);
        return "registerForm";
    }

    @RequestMapping(value = "saveCustomer", method = RequestMethod.POST)
    @Transactional
    public String saveCustomer(@ModelAttribute("customer") CustomerEntity theCustomer) {
        customerService.saveCustomer(theCustomer);
        return "checkEmailNotification";
    }
    
     @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@ModelAttribute("customer") CustomerEntity theCustomer) {
        customerService.saveCustomer(theCustomer);
        return "checkEmailNotification";
    }

}
