/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.controller;

import com.example.Ebanking.entities.AccountEntity;
import com.example.Ebanking.entities.BankEntity;
import com.example.Ebanking.entities.CustomerEntity;
import com.example.Ebanking.repository.BankRepositoryIF;
import com.example.Ebanking.repository.CustomerRepositoryIF;
import com.example.Ebanking.service.AccountService;
import com.example.Ebanking.service.CustomerService;
import com.example.Ebanking.service.RecieptService;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author solid
 */
@Controller
public class AccountController {

    @Autowired
    AccountService accountService;
    @Autowired
    CustomerRepositoryIF customerRepositoryIF;
    @Autowired
    BankRepositoryIF bankRepositoryIF;
    @Autowired
    TranferController tc;
    @Autowired
    RecieptService rs;

    @PostMapping("account/confirmTF")
    public String tfConfirm() {
        return "confirmTranfer";
    }

    @GetMapping("newAccount")
    public String newAccount(Model model, @ModelAttribute AccountEntity account) {
        model.addAttribute("account", new AccountEntity());
        return "adminAccountForm";
    }

    @PostMapping("newAccount")
    public String CreateNewAccount(@ModelAttribute("account") AccountEntity account, Model model, @Param("customerID") int customerID, @Param("bankID") int bankID) {
        CustomerEntity customer = customerRepositoryIF.findByCustomerID(customerID);
        BankEntity bank = bankRepositoryIF.findByBankID(bankID);
        account.setCustomerEntity(customer);
        account.setBankEntity(bank);
        model.addAttribute("account", account);
        accountService.saveAccount(account);
        return "redirect:/list-account";
    }

    @GetMapping(value = "/list-account")
    public String listAccounts(HttpServletRequest request, Model theModel, @Param("keyword") String keyword) {
        List<AccountEntity> accounts = accountService.getAccounts(keyword);
        PagedListHolder pagedListHolder = new PagedListHolder(accounts);
        int page = ServletRequestUtils.getIntParameter(request, "p", 0);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(10);

        theModel.addAttribute("pagedListHolder", pagedListHolder);

        return "adminAccount";
    }

    @GetMapping("viewBallance")
    public String viewAccount(Model model, Principal principal) {
        List<AccountEntity> accList = tc.getListAccType(principal);
        model.addAttribute("listAccount", accList);
        return "viewBallance";
    }

//    TNT
    @GetMapping("printRecieptAcc/{accountID}")
    @ResponseStatus(value = HttpStatus.OK)
    public void viewAccount(@PathVariable("accountID") double accountID, HttpServletResponse response) throws IOException {
        AccountEntity ae = accountService.findByAccountID(accountID);
        rs.pdfAccount(ae, response);
    }

    @GetMapping("withdraw")
    public String withdraw(Model model) {
        AccountEntity account = new AccountEntity();
        model.addAttribute("account", account);
        return "adminWithdraw";
    }

    @PostMapping("withdraw")
    public String withdraw(Model model, @Param("money") double money, @Param("accountID") double accountID) {
        AccountEntity account = accountService.findByAccountID(accountID);
        if (account != null) {
            if (account.getBallance() < money) {
                model.addAttribute("error", "This account is not enough money!");
                return "adminWithdraw";
            } else {
                account.setBallance(account.getBallance() - money);
                accountService.saveAccount(account);
                model.addAttribute("success", "Withdraw is successful!");
                return "adminWithdraw";
            }
        } else {
            model.addAttribute("notExist", "Please check the account again! It may be not existed");
            return "adminWithdraw";
        }

    }

    @GetMapping("deposit")
    public String deposit(Model model) {
        AccountEntity account = new AccountEntity();
        model.addAttribute("account", account);
        return "adminDepositCheckAccount";
    }

    @PostMapping("depositCheck")
    public String depositcheck(Model model, @Param("accountID") double accountID) {
        AccountEntity account = accountService.findByAccountID(accountID);
        if (account != null) {
            String userName = account.getCustomerEntity().getFullName();
            model.addAttribute("userName", userName);
            model.addAttribute("account", account);
            return "adminDeposit";
        } else {
            model.addAttribute("notExist", "Account is not existed");
            return "adminDepositCheckAccount";
        }
    }

    @PostMapping("deposit")
    public String deposit(Model model, @Param("money") double money, @ModelAttribute("account") AccountEntity account) {
        account.setBallance(account.getBallance() + money);
        accountService.saveAccount(account);
        model.addAttribute("success", "Deposit is successful!");
        return "adminDeposit";

    }
}
