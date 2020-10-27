/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.controller;

import com.example.Ebanking.entities.TransactionEntity;
import com.example.Ebanking.service.AccountService;
import com.example.Ebanking.service.CoverCurrencyToText;
import com.example.Ebanking.service.EmailService;
import com.example.Ebanking.service.OTPService;
import com.example.Ebanking.service.RestService;
import com.example.Ebanking.service.TransactionService;
import com.example.Ebanking.service.UserSevice;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author solid
 */
@Controller
public class OTPController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OTPService otpService;
    @Autowired
    private EmailService myEmailService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserSevice userSevice;
    @Autowired
    private CoverCurrencyToText currencyToText;
    @Autowired
    RestService restService;

//    @GetMapping("/generateOtp")
    public String generateOtp(String msg, Model model, TransactionEntity transactionSS) throws JsonProcessingException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        int otp = otpService.generateOTP(username);
        String amountByWords = currencyToText.coverNumberToText(transactionSS.getAmount());
        String email = userSevice.getUserByUserName(username).getCustomerEntity().getEmail();
        String message = Integer.toString(otp);
        double receiverAccID = transactionSS.getReceiverAccount().getAccountID();
        String nameReceiver = getReceiverUs(msg, receiverAccID);
        myEmailService.sendOtpMessage(email, "Verify-OTP", message);
        model.addAttribute("msg", msg);
        model.addAttribute("nameReceiver", nameReceiver);
        model.addAttribute("amountbyWords", amountByWords);
        return "confirmOTP";
    }

    @PostMapping("/confirmOTP")
    @Transactional(rollbackFor = Exception.class)
    public String confirmOTP(@RequestParam(name = "OTPcode") String OTPcode, Model model,
            @SessionAttribute("transaction") TransactionEntity transactionSS, SessionStatus status,
            @RequestParam("msg") String msg) throws JsonProcessingException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        String cacheOTP = Integer.toString(otpService.getOtp(username));
        String amountByWords = currencyToText.coverNumberToText(transactionSS.getAmount());
        double senderAccID = transactionSS.getSenderAccount().getAccountID();
        double receiverAccID = transactionSS.getReceiverAccount().getAccountID();
        double amount = transactionSS.getAmount();
        boolean fee = transactionSS.isFeeBearer();
        String nameReceiver = getReceiverUs(msg, receiverAccID);
        if (cacheOTP.equals(OTPcode)) {
            transactionService.saveTransaction(transactionSS);
            accountService.updateBalance(senderAccID, amount, receiverAccID, fee, msg);
            status.setComplete();
            return "tranferSuccess";
        }
        otpService.clearOTP(username);
        int Otp = otpService.generateOTP(username);
        String message = Integer.toString(Otp);
        String userEmail = transactionSS.getSenderAccount().getCustomerEntity().getEmail();
        myEmailService.sendOtpMessage(userEmail, "Verify-OTP Again!", message);
        model.addAttribute("errorOTP", "OTP Wrong");
        model.addAttribute("msg", msg);
        model.addAttribute("amountbyWords", amountByWords);
        model.addAttribute("nameReceiver", nameReceiver);
        return "confirmOTP";
    }

    public String getReceiverUs(String msg, double receiverAccID) throws JsonProcessingException {
        String nameReceiver = "";
        if (msg.equalsIgnoreCase("internal")) {
            nameReceiver = accountService.findByAccountID(receiverAccID).getCustomerEntity().getFullName();
        } else {
            nameReceiver = restService.checkAccountFromRest(receiverAccID).getCustomerEntity().getFullName();
        }
        return nameReceiver;
    }
}
