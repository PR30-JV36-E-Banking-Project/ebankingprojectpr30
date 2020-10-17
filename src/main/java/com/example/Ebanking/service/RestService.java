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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author solid
 */
@Service
public class RestService {

    public void getAccountFromRest() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8085/accountRest";
        String json = restTemplate.getForObject(URL, String.class);
        ObjectMapper mapper = new ObjectMapper();
        AccountEntity accountEntity = mapper.readValue(json, AccountEntity.class);
        System.out.println(accountEntity.getAccountID() + ", " + accountEntity.getBallance() + ", " + accountEntity.getAccountType());
        System.out.println(accountEntity.getCustomerEntity().getEmail()+ ", " + accountEntity.getCustomerEntity().getFullName());

    }

}
