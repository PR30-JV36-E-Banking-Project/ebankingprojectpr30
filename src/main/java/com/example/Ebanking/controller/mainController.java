/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.controller;

import com.example.Ebanking.repository.CustomerRepositoryIF;
import com.example.Ebanking.repository.TellerRepository;
import com.example.Ebanking.repository.TransactionRepositoryIF;
import java.security.Principal;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author solid
 */
@Controller
public class mainController {

    @Autowired
    private TellerRepository tellerRepository;
    @Autowired
    private CustomerRepositoryIF customerRepositoryIF;
    @Autowired
    private TransactionRepositoryIF transactionRepositoryIF;

    @GetMapping("/")
    public String index(Principal principal, HttpServletRequest request) {
        HttpSession ses = request.getSession();
        if (ses.getAttribute("user") == null) {
            return "index";
        }
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal, HttpServletRequest request, Authentication authResult) {
        String roleArr = authResult.getAuthorities().toString();
        String role = roleArr.substring(1, roleArr.length() - 1);
        String username = principal.getName();
        model.addAttribute("username", username);
        HttpSession ses = request.getSession();
        ses.setAttribute("user", "logged");
        if (role.equals("ROLE_USER")) {
            return "logout";
        } else if (role.equals("ROLE_TELLER")) {
            return "redirect:/teller";
        } else if (role.equals("ROLE_ADMIN")) {
            return "redirect:/admin";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "error2", required = false) String error2,
            @RequestParam(value = "error_3", required = false) String error_3,
            Model model, HttpServletRequest request) {
        HttpSession ses = request.getSession();
        if (ses.getAttribute("user") != null) {
            return "redirect:/home";
        }
        if (error != null) {
            model.addAttribute("error", "Invalid username and password!");
        }
        if (error2 != null) {
            model.addAttribute("error2", "Wrong verification code.!");
        }
        if (error_3 != null) {
            model.addAttribute("error_3", "Show login dialog!");
        }
        return "index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @GetMapping("/403")
    public String deniedAccess() {
        return "403";
    }

    @GetMapping("/teller")
    public String teller() {
        return "redirect:/account/list-customer";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        int numberOfTeller = tellerRepository.getNumberOfTellers();
        int numberOfCustomer = customerRepositoryIF.getNumberOfCustomers();
        float amountOfTransaction = transactionRepositoryIF.getAmountOfTransactions();
        LocalDate today = LocalDate.now();
        model.addAttribute("numberOfTeller", numberOfTeller);
        model.addAttribute("numberOfCustomer", numberOfCustomer);
        model.addAttribute("amountOfTransaction", amountOfTransaction);
        model.addAttribute("today", today);
        return "adminIndex";
    }
}
