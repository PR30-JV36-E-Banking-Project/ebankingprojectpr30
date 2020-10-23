/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.AccountEntity;
import com.example.Ebanking.model.Cusmodel;
import com.example.Ebanking.model.ETFModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Writer;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author solid
 */
@Service
public class RestService {

    @Autowired
    FormatDouble formatDouble;

    public AccountEntity checkAccountFromRest(double accountID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String ID = formatDouble.formatDoubleNum(accountID);
        System.out.println("id " + ID);
        String URL = "http://localhost:8085/accountRest/4";
        System.out.println(URL);
        String json = restTemplate.getForObject(URL, String.class);
        ObjectMapper mapper = new ObjectMapper();
        AccountEntity accountEntity = mapper.readValue(json, AccountEntity.class);
        System.out.println(accountEntity.getAccountID() + ", " + accountEntity.getBallance() + ", " + accountEntity.getAccountType());
        System.out.println(accountEntity.getCustomerEntity().getEmail() + ", " + accountEntity.getCustomerEntity().getFullName());
        return accountEntity;
    }

    public void updateBallanceETF(double accountID, double amount) {

        double ID = Double.valueOf(formatDouble.formatDoubleNum(accountID)).longValue();
        double amountTF = Double.valueOf(formatDouble.formatDoubleNum(amount)).longValue();
        String amountStr = formatDouble.formatDoubleNum(amount);
        System.out.println("Ex " + ID + "amountTF" + amountTF);
        String URL = "http://localhost:8085/accountRest/update/4/"+amountStr;
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountID(4);
        accountEntity.setBallance(60000);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(URL, accountEntity);
    }
}
