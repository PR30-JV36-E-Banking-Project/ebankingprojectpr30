/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.springframework.stereotype.Service;

/**
 *
 * @author solid
 */
@Service
public class FormatDouble {

    public String formatDoubleNum(double num) {
        NumberFormat nf = new DecimalFormat("#############");
        return nf.format(num);
    }
}
