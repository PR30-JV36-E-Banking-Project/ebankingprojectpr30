/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

/**
 *
 * @author solid
 */
@Service
public class OTPService {

    private static final Integer EXPIRE_MINS = 5;

    private LoadingCache<String, Integer> otpCache;

    public OTPService() {
        super();
        otpCache = CacheBuilder.newBuilder().
                expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
            public Integer load(String key) {
                return 0;
            }
        });
    }

    public int generateOTP(String key) {

        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        otpCache.put(key, otp);
        return otp;
    }

    //This method is used to return the OPT number against Key->Key values is username
    public int getOtp(String key) {
        try {
            return otpCache.get(key);
        } catch (Exception e) {
            return 0;
        }
    }

//This method is used to clear the OTP catched already
    public void clearOTP(String key) {
        otpCache.invalidate(key);
    }
}
