/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.entities;

import com.example.Ebanking.validateCustom.AccountValidate;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author solid
 */
@Entity
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionID;
    private String transactionType;
    @Range(min = 50000, max = 300000000, message = "Your Amount must greater 50,000VND")
    private double amount;
    @NotBlank(message = "Content can not blank")
    @Size(max = 30, message = "Your content must under 30 character")
    private String content;
    private boolean feeBearer;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate transactionDate=null;
    @ManyToOne
    @JoinColumn(name = "senderAccountID")
    private AccountEntity senderAccount;
    @ManyToOne
    @JoinColumn(name = "receiveAccountID")
    @AccountValidate
    private AccountEntity receiverAccount;

    public TransactionEntity() {
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public AccountEntity getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(AccountEntity senderAccount) {
        this.senderAccount = senderAccount;
    }

    public AccountEntity getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(AccountEntity receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFeeBearer() {
        return feeBearer;
    }

    public void setFeeBearer(boolean feeBearer) {
        this.feeBearer = feeBearer;
    }

    @Override
    public String toString() {
        return "TransactionEntity{" + "transactionID=" + transactionID + ", transactionType=" + transactionType + ", amount=" + amount + ", content=" + content + ", feeBearer=" + feeBearer + ", transactionDate=" + transactionDate +'}';
    }

}
