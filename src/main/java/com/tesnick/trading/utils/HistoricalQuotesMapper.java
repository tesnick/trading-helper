package com.tesnick.trading.utils;

import eu.verdelhan.ta4j.Tick;
import eu.verdelhan.ta4j.TimeSeries;
import org.joda.time.DateTime;
import yahoofinance.histquotes.HistoricalQuote;

import java.util.ArrayList;
import java.util.List;

public class HistoricalQuotesMapper {

    public static TimeSeries fromHistoricalQuoteToSeries(
            List<HistoricalQuote> historicalQuotes) {

        List<Tick> ticks = new ArrayList<>();
        for (HistoricalQuote quote : historicalQuotes) {
            DateTime date = new DateTime(quote.getDate());
            double open = (quote.getOpen().doubleValue());
            double high = (quote.getHigh().doubleValue());
            double low = (quote.getLow().doubleValue());
            double close = (quote.getClose().doubleValue());
            double volume = (new Long(quote.getVolume()).doubleValue());

            ticks.add(new Tick(date, open, high, low, close, volume));
        }

        return new TimeSeries("A3M.MC", ticks);
    }

}
