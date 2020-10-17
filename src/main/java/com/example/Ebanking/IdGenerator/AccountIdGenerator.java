/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.IdGenerator;

import com.example.Ebanking.entities.AccountEntity;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 *
 * @author solid
 */
public class AccountIdGenerator implements IdentifierGenerator {

    Random r = new Random();
    Session session;

    int attempt = 0;
    
    public double generate13DigitNumber() {
        double aNumber = (double) ((Math.random() * 9000000000000L) + 1000000000000L);
        NumberFormat nf = new DecimalFormat("#");
        double number=Double.valueOf(nf.format(aNumber));
        return number;
    }

    public Double generateRandomIndex() {
        for (int i = 0; i < 3; i++) {
            double a = generate13DigitNumber();

            if (session.get(AccountEntity.class, a) == null) {
                return a;
            } else {
            }
        }

        for (double i = 1000000000000L; i < 9999999999999L; i++) {
            if (session.get(AccountEntity.class, i) == null) {
                return i;
            }
        }
        return null;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor ssci, Object o) throws HibernateException {
        session = (Session) ssci;
        double id = generateRandomIndex();
        return id;
    }
}
