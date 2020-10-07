/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.controller;

import com.example.Ebanking.entities.ConfirmationToken;
import com.example.Ebanking.entities.UserEntity;
import com.example.Ebanking.service.ConfirmationTokenService;
import com.example.Ebanking.service.UserServiceSecurity;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Hoang Duy Nhat
 */
public class UserController {

    private UserServiceSecurity userServiceSecurity;

    private ConfirmationTokenService confirmationTokenService;

    @GetMapping("/sign-up")
    String signUp() {

        return "registerForm";
    }

    @PostMapping("/sign-up")
    String signUp(UserEntity userEntity) {

        userServiceSecurity.signUpUser(userEntity);

        return "checkEmailNotification";
    }

    @GetMapping("/confirm")
    String confirmMail(@RequestParam("token") String token) {

        Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);

        optionalConfirmationToken.ifPresent(userServiceSecurity::confirmUser);

        return "registerSuccess";
    }
}
