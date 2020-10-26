/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.AccountEntity;
import com.example.Ebanking.repository.AccountRepositoryIF;
import java.util.List;
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

    @Override
    public AccountEntity findByAccountType(String accountType) {
        Optional<AccountEntity> account = accountRepositoryIF.findByAccountType(accountType);
        return account.isPresent() ? account.get() : null;
    }

    @Override
    public AccountEntity findByAccountID(int ID) {
        Optional<AccountEntity> account = accountRepositoryIF.findByAccountID(ID);
        return account.isPresent() ? account.get() : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBalance(int senderAccountID, double amount, int recieverAccountID, boolean fee) {
        AccountEntity senderAccount = findByAccountID(senderAccountID);
        AccountEntity recieverAccount = findByAccountID(recieverAccountID);
        if (fee == true) {
            senderAccount.setBallance(senderAccount.getBallance() - amount - 5000);
            recieverAccount.setBallance(recieverAccount.getBallance() + amount);
        } else {
            senderAccount.setBallance(senderAccount.getBallance() - amount);
            recieverAccount.setBallance(recieverAccount.getBallance() + amount - 5000);
        }
        accountRepositoryIF.save(senderAccount);
        accountRepositoryIF.save(recieverAccount);
    }

    @Override
    public boolean checkBalance(int senderAccountID, double amount) {
        double balance = findByAccountID(senderAccountID).getBallance();
        if (balance >= amount + 50000) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void saveAccount(AccountEntity account) {
        accountRepositoryIF.save(account);
    }
    @Override
    public List<AccountEntity> getAccounts(String keyword) {
        if (keyword != null) {
            return accountRepositoryIF.search(keyword);
        }
        return (List)accountRepositoryIF.findAll();
    }
}
