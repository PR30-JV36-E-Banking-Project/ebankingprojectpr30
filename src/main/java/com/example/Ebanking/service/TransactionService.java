/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.TransactionEntity;
import com.example.Ebanking.repository.TransactionRepositoryIF;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author solid
 */
@Service
public class TransactionService implements TransactionServiceIF {

    @Autowired
    TransactionRepositoryIF transactionRepositoryIF;

    @Override
    public void saveTransaction(TransactionEntity transactionEntity) {
        transactionRepositoryIF.save(transactionEntity);
    }

    @Override
    public List<TransactionEntity> getTransactionByDate(Date start, Date end, double id) {
        return transactionRepositoryIF.getTransactionWhereDateBetweenValue(start, end, id);
    }

    @Override
    public TransactionEntity getTransactionByID(int id) {
        Optional<TransactionEntity> transaction = transactionRepositoryIF.findByTransactionID(id);
        return transaction.isPresent() ? transaction.get() : null;
    }

}
