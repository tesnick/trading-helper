package com.tesnick.trading.beans;

import yahoofinance.histquotes.HistoricalQuote;

import java.util.List;

/**
 * Class for YahooFinanceAPI library
 */
public class YahooResults {

    private List<HistoricalQuote> historicalQuotes;

    public List<HistoricalQuote> getHistoricalQuotes() {
        return historicalQuotes;
    }

    public void setHistoricalQuotes(List<HistoricalQuote> historicalQuotes) {
        this.historicalQuotes = historicalQuotes;
    }

}
