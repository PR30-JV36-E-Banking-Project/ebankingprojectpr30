/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author solid
 */
public class ETFModel {

    @JsonProperty
    private int accountID;
    @JsonProperty
    private double ballance;
    @JsonProperty
    private String accountType;
    @JsonProperty
    private Cusmodel cusmodel;

    public ETFModel() {
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

    public Cusmodel getCusmodel() {
        return cusmodel;
    }

    public void setCusmodel(Cusmodel cusmodel) {
        this.cusmodel = cusmodel;
    }

}
