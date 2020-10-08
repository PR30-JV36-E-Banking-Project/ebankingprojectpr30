/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.controller;

import com.example.Ebanking.entities.AccountEntity;
import com.example.Ebanking.entities.TransactionEntity;
import com.example.Ebanking.service.AccountService;
import com.example.Ebanking.service.TransactionService;
import com.example.Ebanking.service.UserSevice;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    TransactionService transactionService;

    @GetMapping("interTranfer")
    public String interTranfer(Model model, Principal principal, Authentication authentication) {
        TransactionEntity transaction = new TransactionEntity();
        Map<AccountEntity, String> accountTypesMap = getListAccType(principal);
        model.addAttribute("transaction", transaction);
        model.addAttribute("listTypeAccount", accountTypesMap);
        return "intranferForm";
    }

    @PostMapping("createTF")
    public String createTF(@Valid @ModelAttribute("transaction") TransactionEntity transaction, BindingResult result, Model model, Principal principal) {
        if (result.hasErrors()) {
            Map<AccountEntity, String> accountTypesMap = getListAccType(principal);
            model.addAttribute("listTypeAccount", accountTypesMap);
            return "intranferForm";
        }
        model.addAttribute("transaction", transaction);
        return "confirmTranfer";
    }

    @PostMapping("confirmTranfer")
    public String confirmTranfer(HttpSession session, HttpServletRequest request,Model model) {
        String captcha = session.getAttribute("captcha_security").toString();
        String verifyCaptcha = request.getParameter("captcha");
        if (captcha.equals(verifyCaptcha)) {
            System.out.println("redirect");
            return "redirect:/generateOtp";
        }
        model.addAttribute("error","Wrong Captcha");
        return "confirmTranfer";
    }

    public Map<AccountEntity, String> getListAccType(Principal principal) {
        Set<AccountEntity> accounts = userSevice.getUserByUserName(principal.getName()).getCustomerEntity().getAccountEntitys();
        Map<AccountEntity, String> accountTypeMap = new LinkedHashMap<>();
        for (AccountEntity account : accounts) {
            System.out.println(account.getAccountID());
            accountTypeMap.put(account, account.getAccountType());
        }
        return accountTypeMap;
    }
}
