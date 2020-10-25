/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.AccountEntity;
import com.example.Ebanking.repository.AccountRepositoryIF;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author solid
 */
@Service
public class AccountService implements AccountServiceIF {

    @Autowired
    AccountRepositoryIF accountRepositoryIF;
    @Autowired
    RestService restService;

    @Override
    public AccountEntity findByAccountType(String accountType) {
        Optional<AccountEntity> account = accountRepositoryIF.findByAccountType(accountType);
        return account.isPresent() ? account.get() : null;
    }

    @Override
    public AccountEntity findByAccountID(double ID) {
        Optional<AccountEntity> account = accountRepositoryIF.findByAccountID(ID);
        return account.isPresent() ? account.get() : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBalance(double senderAccountID, double amount, double recieverAccountID, boolean fee, String msg) {
        AccountEntity senderAccount = findByAccountID(senderAccountID);
        if (msg.equalsIgnoreCase("internal")) {
            AccountEntity recieverAccount = findByAccountID(recieverAccountID);
            if (fee == true) {
                senderAccount.setBallance(senderAccount.getBallance() - amount - 5000);
                recieverAccount.setBallance(recieverAccount.getBallance() + amount);
            } else {
                senderAccount.setBallance(senderAccount.getBallance() - amount);
                recieverAccount.setBallance(recieverAccount.getBallance() + amount - 5000);
            }
            accountRepositoryIF.save(recieverAccount);
        } else if(msg.equalsIgnoreCase("external")){
            if (fee == true) {
                senderAccount.setBallance(senderAccount.getBallance() - amount - 5000);
                restService.updateBallanceETF(recieverAccountID, amount);
            } else {
                senderAccount.setBallance(senderAccount.getBallance() - amount);
                restService.updateBallanceETF(recieverAccountID, amount - 5000);
            }
        }
        accountRepositoryIF.save(senderAccount);
    }

    @Override
    public boolean checkBalance(double senderAccountID, double amount) {
        double balance = findByAccountID(senderAccountID).getBallance();
        return balance >= amount + 50000;
    }

    @Override
    public void saveAccount(AccountEntity account
    ) {
        accountRepositoryIF.save(account);
    }

    @Override
    public boolean checkAccountITF(Double accountID
    ) {
        Optional<AccountEntity> accountEntity = accountRepositoryIF.findByAccountID(accountID);
        return accountEntity != null && accountEntity.isPresent();
    }

}
