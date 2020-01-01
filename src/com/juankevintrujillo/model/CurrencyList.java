package com.juankevintrujillo.model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author juankevintr
 */
public final class CurrencyList {
    private final TreeMap<String, Currency> currencies = new TreeMap<>();

    public CurrencyList() {
        add(new Currency("EUR", "Euro", "€"));
        add(new Currency("GBP", "Libra esterlina", "£"));
        add(new Currency("PLN", "Zloty polaco", "zł"));
        add(new Currency("USD", "Dólar estadounidense", "$"));
    }

    private void add(Currency currency) {
        currencies.put(currency.getCode(), currency);
    }

    public Currency get(String code) {
        return currencies.get(code.toUpperCase());
    }

    public Map<String, Currency> getList() {
        return currencies;
    }
}
