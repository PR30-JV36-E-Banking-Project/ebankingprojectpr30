/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.controller;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Ebanking.entities.ConfirmationToken;
import com.example.Ebanking.entities.CustomerEntity;
import com.example.Ebanking.entities.TellerEntity;
import com.example.Ebanking.entities.UserEntity;
import com.example.Ebanking.repository.CustomerRepositoryIF;
import com.example.Ebanking.repository.UserRepositoryIF;
import com.example.Ebanking.service.ConfirmationTokenService;
import com.example.Ebanking.service.CustomerServiceIF;
import com.example.Ebanking.service.UserServiceSecurity;
import com.example.Ebanking.service.UserSevice;
import java.util.List;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestUtils;

/**
 *
 * @author solid
 */
@Controller
@RequestMapping("account")
public class CustomerController {

    @Autowired
    private CustomerServiceIF customerService;
    @Autowired
    private UserSevice userSevice;
    @Autowired
    UserRepositoryIF userRepository;
    @Autowired
    CustomerRepositoryIF customerRepository;

//    @RequestMapping(value = "showForm", method = RequestMethod.GET)
//    public String showFormForAdd(Model theModel) {
//        CustomerEntity theCustomer = new CustomerEntity();
//        theModel.addAttribute("customer", theCustomer);
//        return "registerForm";
//    }
//
//    @RequestMapping(value = "saveCustomer", method = RequestMethod.POST)
//    @Transactional
//    public String saveCustomer(@ModelAttribute("customer") CustomerEntity theCustomer) {
//        customerService.saveCustomer(theCustomer);
//        return "checkEmailNotification";
//    }
//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    public String login(@ModelAttribute("customer") CustomerEntity theCustomer) {
//        customerService.saveCustomer(theCustomer);
//        return "checkEmailNotification";
//    }
    @Autowired
    private UserServiceSecurity userServiceSecurity;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @GetMapping("/sign-up")
    String signUp(Model model) {
        UserEntity userEntity = new UserEntity();
        //CustomerEntity customerEntity = new CustomerEntity();
        //customerEntity.setUserEntity(userEntity);
        model.addAttribute("user", userEntity);
        //model.addAttribute("customerEntity", userEntity);
        return "registerForm";
    }

    @PostMapping("/sign-up")
    String signUp(@ModelAttribute("user") UserEntity userEntity,Model model) {
        UserEntity existingUser = userRepository.findByEmailIgnoreCase(userEntity.getEmail());
        CustomerEntity customerEntity = userEntity.getCustomerEntity();
        customerEntity.setUserEntity(userEntity);
        //userEntity.setCustomerEntity(customerEntity);
        //model.addAttribute("customerEntity", customerEntity);

        if (existingUser != null) {
            model.addAttribute("error1", "Email is registered");
            return "registerForm";
        } else {
            userServiceSecurity.signUpUser(userEntity, customerEntity);
            return "checkEmailNotification";
        }
//        } else {
//            model.addAttribute("error2", "Please register Customer Account to user our services");
//            return "registerForm";
//        }

    }

    @GetMapping("/confirm")
    String confirmMail(@RequestParam("token") String token) {

        Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);

        optionalConfirmationToken.ifPresent(userServiceSecurity::confirmUser);

        return "registerSuccess";
    }

    @RequestMapping(value = "viewUserInformation", method = RequestMethod.GET)
    public String home(Model model, Principal principal, HttpServletRequest request) {
        String username = principal.getName();

        UserEntity currentUser = userSevice.getUserByUserName(username);

        int currentID = currentUser.getCustomerEntity().getCustomerID();

        CustomerEntity currentCustomer = customerRepository.findByCustomerID(currentID);
//        CustomerEntity currentCustomer = customerService.findByUserEntity(currentUser);
//        
//        model.addAttribute("currentCustomer", currentCustomer);
        model.addAttribute("currentCustomer", currentCustomer);

        return "viewAccountInformation";
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
    public String saveTeller(@ModelAttribute("customer") CustomerEntity customerEntity, Model model) {
	customerRepository.save(customerEntity);
	return "redirect:/account/list-customer";
    }
//    @GetMapping("/updateCustomerForm")
//    public String showFormForUpdate(@RequestParam("customerID") int theId,
//	    Model theModel) {
//	CustomerEntity customerEntity = customerService.getCustomers();
//        UserEntity userEntity = theTeller.getUserEntity1();
//	theModel.addAttribute("teller", theTeller);
//	return "adminTellerFormUpdate";
//    }
//
//    @GetMapping("/deleteTeller")
//    public String deleteTeller(@RequestParam("tellerID") int theId) {
//	tellerService.deleteTeller(theId);
//	return "redirect:/list-teller";
//    }
}
