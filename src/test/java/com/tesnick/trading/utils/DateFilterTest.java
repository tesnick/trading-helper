package com.tesnick.trading.utils;

import com.tesnick.trading.beans.YahooResults;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateFilterTest {

    protected static final Log logger = LogFactory.getLog(DateFilterTest.class);

    private DateFilter dateFilter = new DateFilter();
    private ResultsMapper resultsMapper = new ResultsMapper();

    @Test
    public void filterTest() throws IOException {

        String ticker = "A3M.MC";

        // get file
        File dataFile = FileNamer.getFileFromYahooDataFolder(ticker);

        // load data
        YahooResults yahooResults = resultsMapper.fromFileToJavaYahooApi(dataFile);

        logger.info("Before filter execution, there are " + yahooResults.getHistoricalQuotes().size() + " quotes.");
        Assert.assertEquals(3042, yahooResults.getHistoricalQuotes().size());

        Calendar dateStart = new GregorianCalendar(2015, 4, 25);
        Calendar dateEnd = new GregorianCalendar(2015, 5, 5);
        dateFilter.filter(yahooResults, dateStart, dateEnd);

        logger.info("After filter execution, there are " + yahooResults.getHistoricalQuotes().size() + " quotes.");
        Assert.assertEquals(10, yahooResults.getHistoricalQuotes().size());

        for (HistoricalQuote historicalQuote : yahooResults.getHistoricalQuotes()) {
            logger.info("HistoricalQuote : " + historicalQuote);
        }
    }
}
