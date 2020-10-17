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
import com.example.Ebanking.service.UserSevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
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

//    @GetMapping("/generateOtp")
    public String generateOtp(String msg, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        int otp = otpService.generateOTP(username);
        String email = userSevice.getUserByUserName(username).getCustomerEntity().getEmail();
        String message = Integer.toString(otp);
        myEmailService.sendOtpMessage(email, "Verify-OTP", message);
        model.addAttribute("msg", msg);
        return "confirmOTP";
    }

    @PostMapping("/confirmOTP")
    public String confirmOTP(@RequestParam(name = "OTPcode") String OTPcode, Model model,
            @SessionAttribute("transaction") TransactionEntity transactionSS, SessionStatus status,
            @RequestParam("msg") String msg) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        String cacheOTP = Integer.toString(otpService.getOtp(username));
        double senderAccID = transactionSS.getSenderAccount().getAccountID();
        double recieverAccID = transactionSS.getReceiverAccount().getAccountID();
        double amount = transactionSS.getAmount();
        boolean fee = transactionSS.isFeeBearer();
        if (cacheOTP.equals(OTPcode)) {
                transactionService.saveTransaction(transactionSS);
                accountService.updateBalance(senderAccID, amount, recieverAccID, fee,msg);
//            status.setComplete();
            return "tranferSuccess";
        }
        otpService.clearOTP(username);
        int Otp = otpService.generateOTP(username);
        String message = Integer.toString(Otp);
        myEmailService.sendOtpMessage("solidwork2013@gmail.com", "Verify OTP", message);
        model.addAttribute("error", "OTP Wrong");
        return "confirmOTP";
    }
}
