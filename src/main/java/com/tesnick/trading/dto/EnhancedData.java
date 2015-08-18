package com.tesnick.trading.dto;

import java.util.List;

public class EnhancedData {

    private String ticker;

    private List<EnhancedQuote> quotes;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public List<EnhancedQuote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<EnhancedQuote> quotes) {
        this.quotes = quotes;
    }

}
