package com.tesnick.trading.utils;

import yahoofinance.histquotes.HistoricalQuote;

import java.util.Comparator;

public class QuotesComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof HistoricalQuote && o2 instanceof HistoricalQuote) {
            HistoricalQuote h1 = (HistoricalQuote) o1;
            HistoricalQuote h2 = (HistoricalQuote) o2;

            return h1.getDate().compareTo(h2.getDate());
        }

        throw new IllegalArgumentException("Trying to compare incorrect class on QuotesComparator!");
    }
}
