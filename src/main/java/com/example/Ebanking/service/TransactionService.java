/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.TransactionEntity;
import com.example.Ebanking.repository.TransactionRepositoryIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author solid
 */
@Service
public class TransactionService implements TransactionServiceIF{
    @Autowired
    TransactionRepositoryIF transactionRepositoryIF;

    @Override
    public void saveTransaction(TransactionEntity transactionEntity) {
        transactionRepositoryIF.save(transactionEntity);
    }
    
}
