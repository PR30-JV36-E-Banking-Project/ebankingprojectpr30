/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.TransactionEntity;
import java.util.Date;
import java.util.List;

/**
 *
 * @author solid
 */
public interface TransactionServiceIF {
    public void saveTransaction(TransactionEntity transactionEntity);
    public List<TransactionEntity>getTransactionByDate(Date start,Date end,double id);
    public List<TransactionEntity>getTransactionByDate(Date start,Date end);
    public TransactionEntity getTransactionByID(int id);
    public List <TransactionEntity> getTransactions(String keyword);
}
