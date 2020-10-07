/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.entities;

import com.example.Ebanking.validateCustom.AccountValidate;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author solid
 */
@Entity
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank(message="Account number cannot blank")
    private int accountID;
    private double ballance;
    private String accountType;
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "customerID")
    private CustomerEntity customerEntity;
    @OneToMany(mappedBy = "senderAccount")
    private Set<TransactionEntity> senders;
    @OneToMany(mappedBy = "receiverAccount")
    private Set<TransactionEntity> receivers;
    @ManyToOne
    @JoinColumn(name = "bankID")
    private BankEntity bankEntity;

    public AccountEntity() {
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public double getBallance() {
        return ballance;
    }

    public void setBallance(double ballance) {
        this.ballance = ballance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public BankEntity getBankEntity() {
        return bankEntity;
    }

    public void setBankEntity(BankEntity bankEntity) {
        this.bankEntity = bankEntity;
    }

    public Set<TransactionEntity> getSenders() {
        return senders;
    }

    public void setSenders(Set<TransactionEntity> senders) {
        this.senders = senders;
    }

    public Set<TransactionEntity> getReceivers() {
        return receivers;
    }

    public void setReceivers(Set<TransactionEntity> receivers) {
        this.receivers = receivers;
    }



}
