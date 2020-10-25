/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author solid
 */
@Service
public class CoverCurrencyToText {

    public HashMap<String, String> prefix = new HashMap<String, String>() {
        {
            put("0", "không");
            put("1", "một");
            put("2", "hai");
            put("3", "ba");
            put("4", "bốn");
            put("5", "năm");
            put("6", "sáu");
            put("7", "bảy");
            put("8", "tám");
            put("9", "chín");
        }
    };
    public HashMap<String, String> subPefix = new HashMap<String, String>() {
        {
            put("1", "đồng");
            put("2", "mươi");
            put("3", "trăm");
            put("4", "nghìn");
            put("5", "mươi");
            put("6", "trăm");
            put("7", "triệu");
            put("8", "mươi");
            put("9", "trăm");
            put("10", "tỷ");
            put("11", "mươi");
            put("12", "trăm");
            put("13", "nghìn");
            put("14", "mươi");
            put("15", "trăm");
        }
    };

    public String coverNumberToText(double number) {
        NumberFormat formatter = new DecimalFormat("#");
        String stringNum = String.valueOf(formatter.format(number));
        String result = "";
        boolean start = false;
        for (int i = 0; i < stringNum.length(); i++) {
            if ((stringNum.charAt(i)) == '0' && start == false) {
                ;
            } else {
                start = true;
                if ((stringNum.length() - i) % 3 == 0 && stringNum.charAt(i) != '0') {
                    result += prefix.get(String.valueOf(stringNum.charAt(i))) + " " + subPefix.get(String.valueOf(stringNum.length() - i)) + " ";
                }
                if ((stringNum.length() - i) % 3 == 0 && stringNum.charAt(i) == '0' && stringNum.charAt(i + 1) != '0') {
                    result += prefix.get(String.valueOf(stringNum.charAt(i))) + " " + subPefix.get(String.valueOf(stringNum.length() - i)) + " ";
                }

                if ((stringNum.length() - i) % 3 == 1 && stringNum.charAt(i) != '0') {
                    result += prefix.get(String.valueOf(stringNum.charAt(i))) + " " + subPefix.get(String.valueOf(stringNum.length() - i)) + " ";
                }
                if ((stringNum.length() - i) % 3 == 1 && stringNum.charAt(i) == '0' && stringNum.charAt(i - 1) != '0') {
                    result += subPefix.get(String.valueOf(stringNum.length() - i)) + " ";
                }
                if ((stringNum.length() - i) % 3 == 1 && stringNum.charAt(i) == '0' && i == stringNum.length() - 1) {
                    result += subPefix.get(String.valueOf(stringNum.length() - i)) + " ";
                }

                if ((stringNum.length() - i) % 3 == 2 && stringNum.charAt(i) == '1') {
                    result += "mười" + " ";
                }
                if ((stringNum.length() - i) % 3 == 2 && stringNum.charAt(i) == '0' && stringNum.charAt(i + 1) != '0') {
                    result += "lẻ" + " ";
                }
                if ((stringNum.length() - i) % 3 == 2 && stringNum.charAt(i) != '0' && stringNum.charAt(i) != '1') {
                    result += prefix.get(String.valueOf(stringNum.charAt(i))) + " " + subPefix.get(String.valueOf(stringNum.length() - i)) + " ";
                }
            }

        }
        String output = result.substring(0, 1).toUpperCase() + result.substring(1);
        return output;
    }
}
