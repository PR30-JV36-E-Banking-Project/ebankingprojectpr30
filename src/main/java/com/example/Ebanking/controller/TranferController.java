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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("interTranfer")
    public String interTranfer(Model model, Principal principal, Authentication authentication) {
        TransactionEntity transaction = new TransactionEntity();
        List<AccountEntity> accountTypesMap = getListAccType(principal);
        model.addAttribute("transaction", transaction);
        model.addAttribute("listTypeAccount", accountTypesMap);
        return "intranferForm";
    }

    @PostMapping("createTF")
    public String createTF(@Valid @ModelAttribute("transaction") TransactionEntity transaction,
            BindingResult result, Model model, Principal principal) throws ParseException {
        int senderAccID = transaction.getSenderAccount().getAccountID();
        double amount = transaction.getAmount();
        if (!accountService.checkBalance(senderAccID, amount)) {
            List<AccountEntity> accountTypesMap = getListAccType(principal);
            model.addAttribute("listTypeAccount", accountTypesMap);
            model.addAttribute("error", "Balance not enought");
            return "intranferForm";
        }
        if (result.hasErrors()) {
            List<AccountEntity> accountTypesMap = getListAccType(principal);
            model.addAttribute("listTypeAccount", accountTypesMap);
            return "intranferForm";
        }
        transaction.setReceiverAccount(accountService.findByAccountID(transaction.getReceiverAccount().getAccountID()));
        transaction.setTransactionDate(LocalDate.parse("2020-02-25"));
        model.addAttribute("transaction", transaction);
        return "confirmTranfer";
    }

    @PostMapping("confirmTranfer")
    public String confirmTranfer(HttpSession session, HttpServletRequest request, Model model) {
        String captcha = session.getAttribute("captcha_security").toString();
        String verifyCaptcha = request.getParameter("captcha");
        if (captcha.equals(verifyCaptcha)) {
            return oTPController.generateOtp();
        }
        model.addAttribute("error", "Wrong Captcha");
        return "confirmTranfer";
    }

    @GetMapping("viewTransaction")
    public String viewTransaction(Model model, Principal principal) {
        model.addAttribute("transaction", new TransactionEntity());
        List<AccountEntity> accountTypesMap = getListAccType(principal);
        model.addAttribute("listTypeAccount", accountTypesMap);
        return "viewTransaction";
    }

    @PostMapping("viewTranfer")
    public String vewTranfer(@ModelAttribute("transaction") TransactionEntity ts, @RequestParam("startDay") String startDay,
            @RequestParam("endDay") String endDay, Model model) throws ParseException {
        int accountID = ts.getSenderAccount().getAccountID();
        Date startD = new SimpleDateFormat("yyyy-MM-dd").parse(startDay);
        Date endD = new SimpleDateFormat("yyyy-MM-dd").parse(endDay);
        System.out.println(startD + "  " + endD + "  " + accountID);
        List<TransactionEntity> transactions = transactionService.getTransactionByDate(startD, endD, accountID);
        System.out.println(transactions);
        model.addAttribute("listTransaction", transactions);
        return "transactionInfo";
    }

    public List<AccountEntity> getListAccType(Principal principal) {
        Set<AccountEntity> accounts = userSevice.getUserByUserName(principal.getName()).getCustomerEntity().getAccountEntitys();
        List<AccountEntity> accountEntitys = new ArrayList<>();
        for (AccountEntity account : accounts) {
            accountEntitys.add(account);
        }
        return accountEntitys;
    }
}
