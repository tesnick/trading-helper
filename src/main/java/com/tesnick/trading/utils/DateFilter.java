package com.tesnick.trading.utils;

import com.tesnick.trading.beans.YahooResults;
import yahoofinance.histquotes.HistoricalQuote;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateFilter {

    public void filter(YahooResults yahooResults, Calendar dateStart, Calendar dateEnd) {

        dateEnd.add(Calendar.DATE, 1);

        List<HistoricalQuote> historicalQuoteList = yahooResults.getHistoricalQuotes();

        List<HistoricalQuote> historicalQuoteListToReturn = new ArrayList<>();
        for (HistoricalQuote historicalQuote : historicalQuoteList) {
            if (historicalQuote.getDate().after(dateStart) && historicalQuote.getDate().before(dateEnd)) {
                historicalQuoteListToReturn.add(historicalQuote);
            }
        }

        yahooResults.setHistoricalQuotes(historicalQuoteListToReturn);
    }

}
