package com.tesnick.trading.beans;

import java.util.List;

/**
 * For yahoo json api
 */
public class Results {

    private List<Quote> quote;

    public List<Quote> getQuote() {
        return quote;
    }

    public void setQuote(List<Quote> quote) {
        this.quote = quote;
    }

}
