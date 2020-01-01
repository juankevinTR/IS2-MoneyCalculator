package com.juankevintrujillo.model;

import java.util.Date;

/**
 *
 * @author juankevintr
 */
public class ExchangeRate {
    private final Currency from;
    private final Currency to;
    private final Date date;
    private final double rate;

    public ExchangeRate(Currency from, Currency to, Date date, double rate) {
        this.from = from;
        this.to = to;
	this.date = date;
        this.rate = rate;   
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }
    
    public Date getDate() {
        return date;
    }

    public double getRate() {
        return rate;
    }
}
