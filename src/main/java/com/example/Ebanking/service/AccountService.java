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

}
