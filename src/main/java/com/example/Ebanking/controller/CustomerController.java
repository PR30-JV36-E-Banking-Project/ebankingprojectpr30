/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.controller;

import com.example.Ebanking.entities.ConfirmationToken;
import com.example.Ebanking.entities.CustomerEntity;
import com.example.Ebanking.entities.UserEntity;
import com.example.Ebanking.repository.CustomerRepositoryIF;
import com.example.Ebanking.repository.UserRepositoryIF;
import com.example.Ebanking.service.ConfirmationTokenService;
import com.example.Ebanking.service.CustomerServiceIF;
import com.example.Ebanking.service.UserServiceSecurity;
import com.example.Ebanking.service.UserSevice;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author solid
 */
@Controller
@RequestMapping("/account")
public class CustomerController {

    @Autowired
    private CustomerServiceIF customerService;
    @Autowired
    private UserSevice userSevice;
    @Autowired
    private UserServiceSecurity userServiceSecurity;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;
    @Autowired
    UserRepositoryIF userRepository;
    @Autowired
    CustomerRepositoryIF customerRepository;

    @GetMapping("/sign-up")
    String signUp(Model model) {
        UserEntity userEntity = new UserEntity();
        model.addAttribute("user", userEntity);
        return "registerForm";
    }

    @PostMapping("/sign-up")
    String signUp(@ModelAttribute("user") UserEntity userEntity, Model model) {
        UserEntity existingUser = userRepository.findByEmailIgnoreCase(userEntity.getEmail());
        CustomerEntity customerEntity = userEntity.getCustomerEntity();
        customerEntity.setUserEntity(userEntity);
        if (existingUser != null) {
            model.addAttribute("error1", "Email is registered");
            return "registerForm";
        } else {
            userServiceSecurity.signUpUser(userEntity, customerEntity);
            return "checkEmailNotification";
        }
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@ModelAttribute("customer") CustomerEntity theCustomer) {
        customerService.saveCustomer(theCustomer);
        return "checkEmailNotification";
    }

    @GetMapping("/confirm")
    String confirmMail(@RequestParam("token") String token) {

        Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);

        optionalConfirmationToken.ifPresent(userServiceSecurity::confirmUser);

        return "registerSuccess";
    }

    @RequestMapping(value = "viewCustomerInfor", method = RequestMethod.GET)
    public String home(Model model, Principal principal, HttpServletRequest request) {
        String username = principal.getName();
        UserEntity currentUser = userSevice.getUserByUserName(username);
        int currentID = currentUser.getCustomerEntity().getCustomerID();
        CustomerEntity currentCustomer = customerRepository.findByCustomerID(currentID);
        model.addAttribute("currentCustomer", currentCustomer);
        return "viewCustomerInfo";
    }

    @GetMapping(value = "/list-customer")
    public String listCustomers(HttpServletRequest request, Model theModel, @Param("keyword") String keyword) {
        List<CustomerEntity> customers = customerService.getCustomers(keyword);
        PagedListHolder pagedListHolder = new PagedListHolder(customers);
        int page = ServletRequestUtils.getIntParameter(request, "p", 0);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(10);

        theModel.addAttribute("pagedListHolder", pagedListHolder);

        return "adminCustomer";
    }

    @GetMapping("/showFormForAddCustomer")
    public String showFormForAddCustomer(Model model) {
        CustomerEntity customer = new CustomerEntity();
        model.addAttribute("customer", customer);
        return "adminCustomerForm";
    }

    @PostMapping("/saveCustomer")
    @Transactional
    public String saveCustomer(@ModelAttribute("customer") CustomerEntity customerEntity, Model model) {
        customerRepository.save(customerEntity);
        return "redirect:/account/list-customer";
    }
    
    @GetMapping("/updateCustomerForm")
    public String showFormForUpdate(@RequestParam("customerID") int theId,
	    Model theModel) {
	CustomerEntity theCustomer = customerService.getCustomer(theId);
	theModel.addAttribute("customer", theCustomer);
	return "adminCustomerForm";
    }
    
    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam("customerID") int theId) {
	customerService.deleteCustomer(theId);
	return "redirect:/account/list-customer";
    }
    
    @GetMapping("changePass")
    public String changePass() {
        return "changePass";
    }

    @PostMapping("changePass")
    public String confirmChangePass(@RequestParam String currentPass, @RequestParam String password, @RequestParam String confirmPass,
            Principal principal, Model model) {
        UserEntity ue = userSevice.getUserByUserName(principal.getName());
        if (!((ue.getPassword()).equals(currentPass))) {
            model.addAttribute("errorCurrentPass", "Password is wrong!");
            return "changePass";
        }
        if (!(password.equals(confirmPass))) {
            model.addAttribute("confirmPass", "Password is wrong or not matched!");
            return "changePass";
        }
        return "redirect:/home";
    }
}
