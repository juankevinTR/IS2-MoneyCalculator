package com.juankevintrujillo.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author juankevintr
 */
public final class CurrencyList {
    private final Map<String, Currency> currencies = new HashMap<>();

    public CurrencyList() {
        add(new Currency("EUR","Euro","€"));
	add(new Currency("GBP", "Libra esterlina", "£"));
	add (new Currency("MXN","Peso mexicano","$"));
	add(new Currency("USD","Dólar estadounidense","$"));
    }
    
    public void add(Currency currency) {
        currencies.put(currency.getCode(), currency);
    }

    public Currency get(String code) {
        return currencies.get(code.toUpperCase());
    }
}
