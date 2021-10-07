package br.com.supera.gamestore.util;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

@Component
public class BigDecimalConverter implements Serializable {
    private static final long serialVersionUID = 1L;

    public BigDecimal converter(String value) {

        if(value == null) {
            return null;
        }
        //value = value.replace(".", "");
        //value = value.replace(",", ".");
        //value = value.trim();
        return new BigDecimal(value.replace(",", "."));
    }
}
