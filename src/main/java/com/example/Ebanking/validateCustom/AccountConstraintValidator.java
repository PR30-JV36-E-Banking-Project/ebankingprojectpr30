/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.validateCustom;

import com.example.Ebanking.entities.AccountEntity;
import com.example.Ebanking.repository.AccountRepositoryIF;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author solid
 */
public class AccountConstraintValidator implements ConstraintValidator<AccountValidate, AccountEntity> {

    @Autowired
    private AccountRepositoryIF accountRepositoryIF;

    @Override
    public void initialize(AccountValidate constraintAnnotation) {
    }

    @Override
    public boolean isValid(AccountEntity accountE, ConstraintValidatorContext cvc) {
        return accountE != null && accountRepositoryIF.findByAccountID(accountE.getAccountID()).isPresent();
    }

}
