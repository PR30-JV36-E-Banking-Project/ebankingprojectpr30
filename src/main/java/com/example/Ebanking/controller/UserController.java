/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.controller;

import com.example.Ebanking.entities.ConfirmationToken;
import com.example.Ebanking.entities.UserEntity;
import com.example.Ebanking.repository.UserRepositoryIF;
import com.example.Ebanking.service.ConfirmationTokenService;
import com.example.Ebanking.service.UserServiceSecurity;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Hoang Duy Nhat
 */
@Controller
public class UserController {

    @Autowired
    private UserServiceSecurity userServiceSecurity;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;
    @Autowired
    private UserRepositoryIF userRepositoryIF;

    @GetMapping("/account/showForm")
    String signUp(Model model) {
        UserEntity userEntity = new UserEntity();
        model.addAttribute("user", userEntity);
        return "registerForm";
    }

    @PostMapping("/account/saveCustomer")
    String signUp(@Valid @ModelAttribute("user") UserEntity userEntity, BindingResult result) {
        if (result.hasErrors()) {
            return "registerForm";
        }
        System.out.println(userEntity.getUserName());
//        userRepositoryIF.save(userEntity);
        userServiceSecurity.signUpUser(userEntity);
        return "checkEmailNotification";
    }

//    @GetMapping("/confirm")
//    String confirmMail(@RequestParam("token") String token) {
//
//        Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);
//
//        optionalConfirmationToken.ifPresent(userServiceSecurity::confirmUser);
//
//        return "registerSuccess";
//    }
}
