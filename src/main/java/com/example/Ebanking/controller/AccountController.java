/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.controller;

import com.example.Ebanking.entities.AccountEntity;
import com.example.Ebanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author solid
 */
@Controller
public class AccountController {
    @Autowired
    AccountService accountService;
    
    @PostMapping("account/confirmTF")
    public  String tfConfirm(){
        return "confirmTranfer";
    }
    
    @GetMapping("newAccount")
    public String newAccount(Model model){
        model.addAttribute("account",new AccountEntity());
        return "newAccount";
    }
    @PostMapping("newAccount")
    public String CreateNewAccount(@ModelAttribute AccountEntity account,Model model){
        model.addAttribute("account",new AccountEntity());
        accountService.saveAccount(account);
        return "logout";
    }
}
