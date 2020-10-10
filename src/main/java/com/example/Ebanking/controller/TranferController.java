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
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author solid
 */
@Controller
@SessionAttributes("transaction")
public class TranferController {

    @Autowired
    UserSevice userSevice;
    @Autowired
    AccountService accountService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    OTPController oTPController;

//    @ModelAttribute("transactionSS")
//    public TransactionEntity getTransaction() {
//        return new TransactionEntity();
//    }
    @GetMapping("interTranfer")
    public String interTranfer(Model model, Principal principal, Authentication authentication) {
        TransactionEntity transaction = new TransactionEntity();
//        Map<AccountEntity, String> accountTypesMap = getListAccType(principal);
        List<AccountEntity> accountTypesMap = getListAccType(principal);
        model.addAttribute("transaction", transaction);
        model.addAttribute("listTypeAccount", accountTypesMap);
        return "intranferForm";
    }

    @PostMapping("createTF")
    public String createTF(@Valid @ModelAttribute("transaction") TransactionEntity transaction, BindingResult result, Model model, Principal principal) throws ParseException {
        if (result.hasErrors()) {
//            Map<AccountEntity, String> accountTypesMap = getListAccType(principal);
            List<AccountEntity> accountTypesMap = getListAccType(principal);
            model.addAttribute("listTypeAccount", accountTypesMap);
            return "intranferForm";
        }
//        transaction.setAmount(transaction.getAmount());
//        transaction.setContent(transaction.getContent());
//        transaction.setFeeBearer(transaction.isFeeBearer());
        transaction.setReceiverAccount(accountService.findByAccountID(transaction.getReceiverAccount().getAccountID()));
        transaction.setTransactionDate(LocalDate.parse("2020-02-25"));
//        transaction.setReceiverAccount(accountService.findByAccountID(transaction.getReceiverAccount().getAccountID()));
//        transaction.setSenderAccount(transaction.getSenderAccount());
//        transaction.setTransactionType(transaction.getTransactionType());
//        transactionSS.setTransactionDate(stringDate);
//        transaction.setTransactionDate(stringDate);
//
//        transaction.setReceiverAccount(accountService.findByAccountID(transaction.getReceiverAccount().getAccountID()));
//        TransactionEntity trans= new TransactionEntity();
//        trans.setAmount(50000);
//        trans.setContent("chuyen tien");
//        trans.setTransactionType("internal");
//        trans.setFeeBearer(false);
//        trans.setReceiverAccount(accountService.findByAccountID(7));
//        trans.setSenderAccount(accountService.findByAccountID(3));
//        trans.setTransactionDate(LocalDate.parse("2020-02-25"));
//        System.out.println(trans);
//        transactionService.saveTransaction(trans);
        model.addAttribute("transaction", transaction);
        return "confirmTranfer";
    }

    @PostMapping("confirmTranfer")
    public String confirmTranfer(HttpSession session, HttpServletRequest request, Model model) {
        String captcha = session.getAttribute("captcha_security").toString();
        String verifyCaptcha = request.getParameter("captcha");
        if (captcha.equals(verifyCaptcha)) {
            return oTPController.generateOtp();
//              return "redirect:/generateOtp";
        }
        model.addAttribute("error", "Wrong Captcha");
        return "confirmTranfer";
    }

    @GetMapping("viewTransaction")
    public String viewTransaction(Model model) {
        model.addAttribute("transaction", new TransactionEntity());
        return "viewTransaction";
    }

    public List<AccountEntity> getListAccType(Principal principal) {
        Set<AccountEntity> accounts = userSevice.getUserByUserName(principal.getName()).getCustomerEntity().getAccountEntitys();
//        Map<AccountEntity, String> accountTypeMap = new LinkedHashMap<>();
        List<AccountEntity> accountEntitys = new ArrayList<>();
        for (AccountEntity account : accounts) {
//            System.out.println(account.toString());
//            accountTypeMap.put(account, account.getAccountType());
            accountEntitys.add(account);
        }
        return accountEntitys;
    }
}
