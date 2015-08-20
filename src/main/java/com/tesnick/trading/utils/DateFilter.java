package com.tesnick.trading.utils;

import com.tesnick.trading.beans.YahooResults;
import yahoofinance.histquotes.HistoricalQuote;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class DateFilter {

    public void filter(YahooResults yahooResults, Calendar dateStart, Calendar dateEnd) {

        dateEnd.add(Calendar.DATE, 1);

        List<HistoricalQuote> historicalQuoteList = yahooResults.getHistoricalQuotes();

        List<HistoricalQuote> historicalQuoteListToReturn = historicalQuoteList.stream().filter(
                historicalQuote -> historicalQuote.getDate().after(dateStart) && historicalQuote.getDate().before(dateEnd)
                                                                                                ).collect(Collectors.toList());

        yahooResults.setHistoricalQuotes(historicalQuoteListToReturn);
    }

}
