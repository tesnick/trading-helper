package com.tesnick.trading.utils;

import com.tesnick.trading.beans.Quote;
import com.tesnick.trading.beans.Results;
import com.tesnick.trading.dto.EnhancedData;
import com.tesnick.trading.dto.EnhancedQuote;
import com.tesnick.trading.indicators.MovingAverage;

import java.util.ArrayList;
import java.util.List;

public class YahooMapper {

    public EnhancedData convert(Results results) {

        if (results.getQuote().isEmpty()) {
            return new EnhancedData();
        }

        EnhancedData data = new EnhancedData();
        List<EnhancedQuote> enhancedQuotes = new ArrayList<>();

        data.setTicker(results.getQuote().get(0).getSymbol());

        MovingAverage ma6 = new MovingAverage(6);
        MovingAverage ma70 = new MovingAverage(70);
        MovingAverage ma200 = new MovingAverage(200);
        for (Quote quote : results.getQuote()) {

            ma6.newNum(Double.parseDouble(quote.getClose()));
            ma70.newNum(Double.parseDouble(quote.getClose()));
            ma200.newNum(Double.parseDouble(quote.getClose()));

            EnhancedQuote enhancedQuote = new EnhancedQuote();

            enhancedQuote.setDate(quote.getDate());

            enhancedQuote.setVolume(quote.getVolume());

            enhancedQuote.setOpen(quote.getOpen());
            enhancedQuote.setClose(quote.getClose());

            enhancedQuote.setHigh(quote.getHigh());
            enhancedQuote.setLow(quote.getLow());

            enhancedQuote.setMa6(ma6.getAvg());
            enhancedQuote.setMa70(ma70.getAvg());
            enhancedQuote.setMa200(ma200.getAvg());

            enhancedQuote.setSymbol(quote.getSymbol());

            enhancedQuotes.add(enhancedQuote);
        }

        data.setQuotes(enhancedQuotes);

        return data;

    }
}
