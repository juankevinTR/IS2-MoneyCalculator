package com.juankevintrujillo.main;

import com.juankevintrujillo.model.Currency;
import com.juankevintrujillo.model.CurrencyList;
import com.juankevintrujillo.model.ExchangeRate;
import com.juankevintrujillo.model.Money;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author juankevintr
 */
public class Controller {

    private Currency currencyTo;
    private Money money;

    private final String API_ACCESS_KEY = System.getenv("API_ACCESS_KEY"); // Put your Access Key

    public Controller(String amount, String currencyTo) {
        CurrencyList currencyList = new CurrencyList();

        Currency currencyFrom = currencyList.get("EUR");
        money = new Money(Double.parseDouble(amount), currencyFrom);

        this.currencyTo = currencyList.get(currencyTo);
    }

    public String execute() {
        ExchangeRate exchangeRate = getExchangeRate(money.getCurrency(), currencyTo);
        DecimalFormat dotFormat = new DecimalFormat("#,##0.00000");
        return dotFormat.format(money.getAmount() * exchangeRate.getRate());
    }

    private ExchangeRate getExchangeRate(Currency from, Currency to) {
        String url = "http://data.fixer.io/api/latest?access_key="
                + API_ACCESS_KEY
                + "&symbols=" + to.getCode();
        JSONObject json = readJsonFromUrl(url);

        // We get Array where it's all data from JSON
        assert json != null;

        String rate = json.getJSONObject("rates").get(to.getCode()).toString();
        double value = Double.parseDouble(rate);
        return new ExchangeRate(from, to, new Date(), value);
    }

    private JSONObject readJsonFromUrl(String url) throws JSONException {
        StringBuilder sb = new StringBuilder();
        int cp;

        try (InputStream jsonReceived = new URL(url).openStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(jsonReceived, StandardCharsets.UTF_8));

            while ((cp = reader.read()) != -1) {
                sb.append((char) cp); // cp = ASCII Decimal number
            }
            return new JSONObject(sb.toString());
        } catch (Exception e) {
            System.out.println("\tError: we cannot get JSON FILE from the API");
        }
        return null;
    }
}
