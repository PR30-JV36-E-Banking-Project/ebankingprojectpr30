/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.controller;

import com.example.Ebanking.entities.AccountEntity;
import com.example.Ebanking.entities.CustomerEntity;
import com.example.Ebanking.repository.AccountRepositoryIF;
import com.example.Ebanking.repository.CustomerRepositoryIF;
import com.example.Ebanking.service.RestService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author solid
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/accountRest")
public class RestController {

    @Autowired
    AccountRepositoryIF accountRepositoryIF;
    @Autowired
    CustomerRepositoryIF customerRepositoryIF;
    @Autowired
    RestService restService;

    @RequestMapping(method = RequestMethod.GET)
    public Object getAllAccount() {
//        Optional<CustomerEntity> listAcc =  customerRepositoryIF.findByEmail("thaingoctam11cdt3@gmail.com");
        Optional<AccountEntity> listAcc = accountRepositoryIF.findByAccountID(4);
        return listAcc;
    }
}
