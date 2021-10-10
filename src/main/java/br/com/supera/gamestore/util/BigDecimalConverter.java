package br.com.supera.gamestore.util;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: Deivisson Santos
 * @version: 1.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 */

@Component
public class BigDecimalConverter implements Serializable {
    private static final long serialVersionUID = 1L;

    public BigDecimal converter(String value) {

        if(value == null) {
            return null;
        }
        value = value.replace(",", ".");
        return new BigDecimal(value);
    }
}
