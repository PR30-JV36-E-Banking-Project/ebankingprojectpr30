/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.AccountEntity;
import java.util.Optional;



/**
 *
 * @author solid
 */
public interface AccountServiceIF {
    public AccountEntity findByAccountID(double ID);
    public AccountEntity findByAccountType(String accountType);
    public void updateBalance(double senderAccountID,double amount,double receiverAccountID,boolean  fee);
    public boolean checkBalance(double senderAccountID,double amount);
    public void saveAccount(AccountEntity account);
}
