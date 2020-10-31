/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.controller;

import com.example.Ebanking.entities.AccountEntity;
import com.example.Ebanking.entities.TransactionEntity;
import com.example.Ebanking.service.AccountService;
import com.example.Ebanking.service.CoverCurrencyToText;
import com.example.Ebanking.service.EmailService;
import com.example.Ebanking.service.RecieptService;
import com.example.Ebanking.service.RestService;
import com.example.Ebanking.service.TransactionService;
import com.example.Ebanking.service.UserSevice;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.security.Principal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author solid
 */
@Controller
@SessionAttributes("transaction")
public class TranferController {

    @Autowired
    UserSevice userSevice;
    @Autowired
    AccountService accountService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    OTPController oTPController;
    @Autowired
    RecieptService recieptService;
    @Autowired
    EmailService emailService;
    @Autowired
    RestService restService;
    @Autowired
    CoverCurrencyToText currencyToText;

    @GetMapping("newTranfer")
    public String newTranfer() {
        return "selectTFForm";
    }

    @GetMapping("selectTF")
    public String interTranfer(Model model, Principal principal, Authentication authentication, @RequestParam("typeTF") String typeTF) {
        TransactionEntity transaction = new TransactionEntity();
        List<AccountEntity> accountTypesMap = getListAccType(principal);
        model.addAttribute("transaction", transaction);
        model.addAttribute("listTypeAccount", accountTypesMap);
        if (typeTF.equals("1")) {
            model.addAttribute("action", "/createITF");
            model.addAttribute("tittle", "Internal Transaction");
            return "tranferForm";
        } else {
            model.addAttribute("action", "/createETF");
            model.addAttribute("tittle", "External Transaction");
            return "tranferForm";
        }
    }

    @PostMapping("createITF")
    public String createITF(@Valid @ModelAttribute("transaction") TransactionEntity transaction,
            BindingResult result, Model model, Principal principal) throws ParseException {
        if (result.hasErrors()) {
            List<AccountEntity> accountTypesMap = getListAccType(principal);
            model.addAttribute("listTypeAccount", accountTypesMap);
            model.addAttribute("tittle", "Internal Transaction");
            return "tranferForm";
        }
        double senderAccID = transaction.getSenderAccount().getAccountID();
        double receiverAccID = transaction.getReceiverAccount().getAccountID();
        double amount = transaction.getAmount();
        String amountbyWords = currencyToText.coverNumberToText(amount);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        if (accountService.checkAccountITF(receiverAccID) == false) {
            List<AccountEntity> accountTypesMap = getListAccType(principal);
            model.addAttribute("listTypeAccount", accountTypesMap);
            model.addAttribute("tittle", "Internal Transaction");
            model.addAttribute("errorAccount", "Account not valid");
            return "tranferForm";
        }
        if (accountService.checkBalance(senderAccID, amount) == false) {
            List<AccountEntity> accountTypesMap = getListAccType(principal);
            model.addAttribute("listTypeAccount", accountTypesMap);
            model.addAttribute("tittle", "Internal Transaction");
            model.addAttribute("error", "Balance not enought");
            return "tranferForm";
        }
        String nameReceiver = accountService.findByAccountID(receiverAccID).getCustomerEntity().getFullName();
        transaction.setReceiverAccount(accountService.findByAccountID(transaction.getReceiverAccount().getAccountID()));
        transaction.setTransactionDate(LocalDate.parse(dtf.format(now)));
        model.addAttribute("transaction", transaction);
        model.addAttribute("msg", "internal");
        model.addAttribute("amountbyWords", amountbyWords);
        model.addAttribute("nameReceiver", nameReceiver);
        return "confirmTranfer";
    }

    @PostMapping("createETF")
    public String createETF(@Valid @ModelAttribute("transaction") TransactionEntity transaction,
            BindingResult result, Model model, Principal principal) throws JsonProcessingException {
        double senderAccID = transaction.getSenderAccount().getAccountID();
        double receiverAccID = transaction.getReceiverAccount().getAccountID();
        double amount = transaction.getAmount();
        String amountbyWords = currencyToText.coverNumberToText(amount);
        String nameReceiver = restService.checkAccountFromRest(receiverAccID).getCustomerEntity().getFullName();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        if (result.hasErrors()) {
            List<AccountEntity> accountTypesMap = getListAccType(principal);
            model.addAttribute("listTypeAccount", accountTypesMap);
            model.addAttribute("tittle", "External Transaction");
            return "tranferForm";
        } else if (restService.checkAccountFromRest(receiverAccID) == null) {
            List<AccountEntity> accountTypesMap = getListAccType(principal);
            model.addAttribute("listTypeAccount", accountTypesMap);
            model.addAttribute("tittle", "External Transaction");
            model.addAttribute("errorAccount", "Account not valid");
            return "tranferForm";
        } else if (!accountService.checkBalance(senderAccID, amount)) {
            List<AccountEntity> accountTypesMap = getListAccType(principal);
            model.addAttribute("listTypeAccount", accountTypesMap);
            model.addAttribute("tittle", "External Transaction");
            model.addAttribute("error", "Balance not enought");
            return "tranferForm";
        }
        transaction.setTransactionDate(LocalDate.parse(dtf.format(now)));
        model.addAttribute("transaction", transaction);
        model.addAttribute("msg", "external");
        model.addAttribute("amountbyWords", amountbyWords);
        model.addAttribute("nameReceiver", nameReceiver);
        return "confirmTranfer";
    }

    @PostMapping("confirmTranfer")
    public String confirmTranfer(HttpSession session, HttpServletRequest request, Model model,
            @RequestParam("msg") String msg, @SessionAttribute("transaction") TransactionEntity transactionSS) throws JsonProcessingException {
        String captcha = session.getAttribute("captcha_security").toString();
        String verifyCaptcha = request.getParameter("captcha");
        if (captcha.equals(verifyCaptcha)) {
            return oTPController.generateOtp(msg, model, transactionSS);
        }
        model.addAttribute("error", "Wrong Captcha");
        return "confirmTranfer";
    }

    @GetMapping("viewTransaction")
    public String viewTransaction(Model model, Principal principal) {
        model.addAttribute("transaction", new TransactionEntity());
        List<AccountEntity> accountTypesMap = getListAccType(principal);
        model.addAttribute("listTypeAccount", accountTypesMap);
        return "viewTransaction";
    }

    @PostMapping("viewTranfer")
    public String viewTranfer(@ModelAttribute("transaction") TransactionEntity ts, @RequestParam("startDay") String startDay,
            @RequestParam("endDay") String endDay, Model model) throws ParseException {
        Date startD = new SimpleDateFormat("yyyy-MM-dd").parse(startDay);
        Date endD = new SimpleDateFormat("yyyy-MM-dd").parse(endDay);
        NumberFormat formatter = new DecimalFormat("#############");
        double accountID = Double.valueOf(formatter.format(ts.getSenderAccount().getAccountID())).longValue();
        List<TransactionEntity> transactions = transactionService.getTransactionByDate(startD, endD, accountID);
        model.addAttribute("listTransaction", transactions);
        return "transactionInfo";
    }

    @GetMapping("printReciept/{transactionID}")
    @ResponseStatus(value = HttpStatus.OK)
    public void printReciept(@PathVariable("transactionID") int id, HttpServletResponse response) throws IOException, MessagingException {
        TransactionEntity transactionEntity = transactionService.getTransactionByID(id);
        recieptService.createPdf(transactionEntity, response);
    }

    @PostMapping("getUsername")
    @ResponseBody
    public String getUsetName(@RequestParam double accountID, @RequestParam String tittle) throws JsonProcessingException {
        if (tittle.equalsIgnoreCase("Internal Transaction")) {
            try {
                AccountEntity accountEntity = accountService.findByAccountID(accountID);
                return accountEntity.getCustomerEntity().getFullName();
            } catch (NullPointerException ex) {
                return "Not Found";
            }
        } else {
            try {

                AccountEntity accountEntity = restService.checkAccountFromRest(accountID);
                return accountEntity.getCustomerEntity().getFullName();
            } catch (NullPointerException ex) {
                return "Not Found";
            }
        }
    }

    public List<AccountEntity> getListAccType(Principal principal) {
        Set<AccountEntity> accounts = userSevice.getUserByUserName(principal.getName()).getCustomerEntity().getAccountEntitys();
        List<AccountEntity> accountEntitys = new ArrayList<>();
        for (AccountEntity account : accounts) {
            accountEntitys.add(account);
        }
        return accountEntitys;
    }

    @GetMapping(value = "/list-transaction")
    public String listTransactions(HttpServletRequest request, Model theModel, @Param("keyword") String keyword) {
        List<TransactionEntity> transactions = transactionService.getTransactions(keyword);
        PagedListHolder pagedListHolder = new PagedListHolder(transactions);
        int page = ServletRequestUtils.getIntParameter(request, "p", 0);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(10);

        theModel.addAttribute("pagedListHolder", pagedListHolder);

        return "adminTransaction";
    }

}
