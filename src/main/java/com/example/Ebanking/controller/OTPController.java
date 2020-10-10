/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.controller;

import com.example.Ebanking.entities.TransactionEntity;
import com.example.Ebanking.service.AccountService;
import com.example.Ebanking.service.EmailService;
import com.example.Ebanking.service.OTPService;
import com.example.Ebanking.service.TransactionService;
import java.util.HashMap;
import java.util.Map;
import javax.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

//    @GetMapping("/generateOtp")
    public String generateOtp() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        int otp = otpService.generateOTP(username);
        String message = Integer.toString(otp);
        logger.info("OTP : " + otp);
        myEmailService.sendOtpMessage("solidwork2013@gmail.com", "OTP -SpringBoot", message);

        return "confirmOTP";
    }

    @PostMapping("/confirmOTP")
    public String confirmOTP(@RequestParam(name = "OTPcode") String OTPcode, Model model, @SessionAttribute("transaction") TransactionEntity transactionSS, SessionStatus status) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        String cacheOTP = Integer.toString(otpService.getOtp(username));
        int senderAccID = transactionSS.getSenderAccount().getAccountID();
        int recieverAccID = transactionSS.getReceiverAccount().getAccountID();
        double amount = transactionSS.getAmount();
        boolean fee = transactionSS.isFeeBearer();
        if (cacheOTP.equals(OTPcode)) {
            transactionService.saveTransaction(transactionSS);
            accountService.updateBalance(senderAccID, amount, recieverAccID, fee);
            status.setComplete();
            return "registerSuccess";
        }
        otpService.clearOTP(username);
        int Otp = otpService.generateOTP(username);
        String message = Integer.toString(Otp);
        myEmailService.sendOtpMessage("solidwork2013@gmail.com", "Verify OTP", message);
        model.addAttribute("error", "OTP Wrong");
        return "confirmOTP";
    }
}
