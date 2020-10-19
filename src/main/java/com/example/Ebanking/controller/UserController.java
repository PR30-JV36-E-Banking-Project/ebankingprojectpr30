/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.controller;

import com.example.Ebanking.entities.ConfirmationToken;
import com.example.Ebanking.entities.UserEntity;
import com.example.Ebanking.repository.CustomerRepositoryIF;
import com.example.Ebanking.repository.UserRepositoryIF;
import com.example.Ebanking.service.ConfirmationTokenService;
import com.example.Ebanking.service.CustomerServiceIF;
import com.example.Ebanking.service.UserServiceSecurity;
import com.example.Ebanking.service.UserSevice;
import java.security.Principal;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Hoang Duy Nhat
 */
@Controller
@RequestMapping("user")
public class UserController {
    
    
    @Autowired
    private CustomerServiceIF customerService;
    @Autowired
    private UserSevice userSevice;
    @Autowired
    UserRepositoryIF userRepository;
    @Autowired
    CustomerRepositoryIF customerRepository;
    @Autowired
    private UserServiceSecurity userServiceSecurity;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;
    @Autowired
    private UserRepositoryIF userRepositoryIF;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/updatePassword")
    public String updatePassword() {
	return "changePassword";
    }
    @PostMapping("/updatePassword")
    public String updatePassword(@RequestParam("password") String password,@RequestParam("oldPassword") String oldPassword ,Model model, Principal principal){
        String username = principal.getName();
        UserEntity currentUser = userSevice.getUserByUserName(username);
        String passDB = passwordEncoder.encode(oldPassword);
        if(passwordEncoder.matches(oldPassword, currentUser.getPassword())){
            userServiceSecurity.updatePassword(currentUser, password);
            model.addAttribute("success","Change Password Successful");
            return "changePassword";
        } else {
        model.addAttribute("error","Change Password Failed");
        return "changePassword";
        }
    }
    
    
//    @GetMapping("/account/showForm")
//    String signUp(Model model) {
//        UserEntity userEntity = new UserEntity();
//        model.addAttribute("user", userEntity);
//        return "registerForm";
//    }
//
//    @PostMapping("/account/signup")
//    String signUp(@Valid @ModelAttribute("user") UserEntity userEntity, BindingResult result) {
//        if (result.hasErrors()) {
//            return "registerForm";
//        }
////        userRepositoryIF.save(userEntity);
//        userServiceSecurity.signUpUser(userEntity);
//        return "checkEmailNotification";
//    }

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
