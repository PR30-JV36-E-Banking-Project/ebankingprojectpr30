/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.controller;

import com.example.Ebanking.entities.AccountEntity;
import com.example.Ebanking.service.AccountService;
import com.example.Ebanking.service.RecieptService;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author solid
 */
@Controller
public class AccountController {

    @Autowired
    AccountService accountService;
    @Autowired
    TranferController tc;
    @Autowired
    RecieptService rs;

    @PostMapping("account/confirmTF")
    public String tfConfirm() {
        return "confirmTranfer";
    }

    @GetMapping("newAccount")
    public String newAccount(Model model) {
        model.addAttribute("account", new AccountEntity());
        return "newAccount";
    }

    @PostMapping("newAccount")
    public String CreateNewAccount(@ModelAttribute AccountEntity account, Model model) {
        model.addAttribute("account", new AccountEntity());
        accountService.saveAccount(account);
        return "logout";
    }

    @GetMapping("viewAccount")
    public String viewAccount(Model model, Principal principal) {
        List<AccountEntity> accList = tc.getListAccType(principal);
        model.addAttribute("listAccount", accList);
        return "accountProfile";
    }

    @GetMapping("printRecieptAcc/{accountID}")
    @ResponseStatus(value = HttpStatus.OK)
    public void viewAccount(@PathVariable("accountID") double accountID, HttpServletResponse response) throws IOException {
        AccountEntity ae = accountService.findByAccountID(accountID);
        rs.pdfAccount(ae, response);
    }
    

}
