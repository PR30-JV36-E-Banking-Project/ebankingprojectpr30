/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.controller;

import com.example.Ebanking.entities.AccountEntity;
import com.example.Ebanking.entities.TransactionEntity;
import com.example.Ebanking.service.AccountService;
import com.example.Ebanking.service.UserSevice;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author solid
 */
@Controller
public class TranferController {

    @Autowired
    UserSevice userSevice;
    @Autowired
    AccountService accountService;

    @GetMapping("interTranfer")
    public String interTranfer(Model model, Principal principal, Authentication authentication) {
        TransactionEntity transaction = new TransactionEntity();
        Set<String> accountTypes = getListAccType(principal);
        model.addAttribute("transaction", transaction);
        model.addAttribute("listTypeAccount", accountTypes);
        return "intranferForm";
    }

    @PostMapping("createTF")
    public String createTF(@Valid @ModelAttribute("transaction") TransactionEntity transaction, BindingResult result, Model model, Principal principal) {
        if (result.hasErrors()) {
            Set<String> accountTypes = getListAccType(principal);
            model.addAttribute("listTypeAccount", accountTypes);
            return "intranferForm";
        }
//        AccountEntity accountEntity;
//        accountEntity = accountService.findByAccountType(transaction.getSenderAccount().getAccountType());
//        transaction.setReceiverAccount(accountEntity);
        model.addAttribute("transaction", transaction);
        return "confirmTranfer";
    }

    public Set<String> getListAccType(Principal principal) {
        Set<String> accountTypes = new HashSet<>();
        Set<AccountEntity> accounts = userSevice.getUserByUserName(principal.getName()).getCustomerEntity().getAccountEntitys();
        for (AccountEntity account : accounts) {
            accountTypes.add(account.getAccountType());
        }
        return accountTypes;
    }
}
