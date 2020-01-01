package com.juankevintrujillo.main;

import com.juankevintrujillo.model.Currency;
import com.juankevintrujillo.model.CurrencyList;
import com.juankevintrujillo.model.ExchangeRate;
import com.juankevintrujillo.model.Money;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author juankevintr
 */
public class Controller {

    private final CurrencyList currencyList;
    private Money money;
    private ExchangeRate exchangeRate;
    private Currency currencyTo;

    private final String API_ACCESS_KEY = "7b81b18577d4106caf27b74e9a325e08";

    public Controller() {
	this.currencyList = new CurrencyList();
    }

    public void execute() throws Exception {
	input();
	process();
	output();
    }

    private void input() {
	System.out.print("Write an amount in EUR: ");
	Scanner scanner = new Scanner(System.in);
	double amount = Double.parseDouble(scanner.next());

	// Free plan of the API  make exchange from EUR to another currency only
	while (true) {
	    Currency currency = currencyList.get("EUR");
	    money = new Money(amount, currency);
	    if (currency != null) {
		break;
	    }
	    System.out.println("Currency not found");
	}

	while (true) {
	    System.out.print("Write the currency to convert: ");
	    currencyTo = currencyList.get(scanner.next());
	    if (currencyTo != null) {
		break;
	    }
	    System.out.println("Currency not found");
	}
    }

    private void process() throws Exception {
	exchangeRate = getExchangeRate(money.getCurrency(), currencyTo);
    }

    private void output() {
	System.out.println("|------------------------------");
	System.out.println("| From " 
		+ money.getCurrency().getCode() 
		+ " to " 
		+ currencyTo.getCode()
		+ "\t");
	System.out.println("|\n| " + money.getAmount() + money.getCurrency().getSymbol() + " ---> " + money.getAmount() * exchangeRate.getRate() + currencyTo.getSymbol());
	System.out.println("|------------------------------");

    }

    private ExchangeRate getExchangeRate(Currency from, Currency to) throws Exception {
	String url = "http://data.fixer.io/api/latest?access_key="
		+ API_ACCESS_KEY
		+ "&symbols=" + to.getCode();
	JSONObject json = readJsonFromUrl(url);

	// We get Array where it's all data from JSON
	assert json != null;

	String rate = json.getJSONObject("rates").get(to.getCode()).toString();
	Double value = Double.parseDouble(rate);
	return new ExchangeRate(from, to, new Date(), value);
    }

    private JSONObject readJsonFromUrl(String url) throws JSONException {
	StringBuilder sb = new StringBuilder();
	int cp;

	try (InputStream jsonReceived = new URL(url).openStream()) {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(jsonReceived, Charset.forName("UTF-8")));

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
