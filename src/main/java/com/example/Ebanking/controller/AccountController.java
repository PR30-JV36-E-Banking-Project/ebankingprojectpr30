/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author solid
 */
@Controller
public class AccountController {
    @PostMapping("account/confirmTF")
    public  String tfConfirm(){
        return "confirmTranfer";
    }
}
