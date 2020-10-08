/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.controller;

import com.example.Ebanking.service.EmailService;
import com.example.Ebanking.service.OTPService;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author solid
 */
@Controller
public class OTPController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public OTPService otpService;
    @Autowired
    public EmailService myEmailService;

    @GetMapping("/generateOtp")
    public String generateOtp() {
        System.out.println("sen Otp");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        int otp = otpService.generateOTP(username);
        String message = Integer.toString(otp);
        logger.info("OTP : " + otp);
        myEmailService.sendOtpMessage("solidwork2013@gmail.com", "OTP -SpringBoot", message);

        return "confirmOTP";
    }

    @PostMapping("/confirmOTP")
    public String confirmOTP(@RequestParam(name = "OTPcode") String OTPcode,Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        String cacheOTP = Integer.toString(otpService.getOtp(username));
        if (cacheOTP.equals(OTPcode)) {
            return"registerSuccess";
        }
        otpService.clearOTP(username);
        int Otp=otpService.generateOTP(username);
        String message = Integer.toString(Otp);
        myEmailService.sendOtpMessage("solidwork2013@gmail.com", "Verify OTP", message);
        model.addAttribute("error","OTP Wrong");
        return "confirmOTP";
    }
}
