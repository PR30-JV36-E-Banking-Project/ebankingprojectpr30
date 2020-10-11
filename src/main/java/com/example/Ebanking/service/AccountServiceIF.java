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
    public AccountEntity findByAccountID(int ID);
    public AccountEntity findByAccountType(String accountType);
    public void updateBalance(int senderAccountID,double amount,int receiverAccountID,boolean  fee);
    public boolean checkBalance(int senderAccountID,double amount);
    public void saveAccount(AccountEntity account);
}
