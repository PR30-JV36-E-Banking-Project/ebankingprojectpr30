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
public class Cusmodel {
    @JsonProperty
    private String fullName;
    @JsonProperty
    private String email;

    public Cusmodel() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
