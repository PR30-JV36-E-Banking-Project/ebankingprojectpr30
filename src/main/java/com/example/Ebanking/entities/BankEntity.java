/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.entities;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author solid
 */
@Entity
public class BankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bankID;
    private String bankName;
    private String bankPhone;
    private String bankEmail;
    private String bankAddress;
    @OneToMany(mappedBy = "bankEntity")
    private Set<AccountEntity> accountEntitys;

    @OneToMany(mappedBy = "bankEntity")
    private Set<TellerEntity> tellerEntitys;

    public BankEntity() {
    }

    public int getBankID() {
        return bankID;
    }

    public void setBankID(int bankID) {
        this.bankID = bankID;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankPhone() {
        return bankPhone;
    }

    public void setBankPhone(String bankPhone) {
        this.bankPhone = bankPhone;
    }

    public String getBankEmail() {
        return bankEmail;
    }

    public void setBankEmail(String bankEmail) {
        this.bankEmail = bankEmail;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public Set<AccountEntity> getAccountEntitys() {
        return accountEntitys;
    }

    public void setAccountEntitys(Set<AccountEntity> accountEntitys) {
        this.accountEntitys = accountEntitys;
    }

    public Set<TellerEntity> getTellerEntitys() {
        return tellerEntitys;
    }

    public void setTellerEntitys(Set<TellerEntity> tellerEntitys) {
        this.tellerEntitys = tellerEntitys;
    }

}
