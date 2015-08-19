package com.tesnick.trading;

import com.tesnick.trading.beans.Query;
import com.tesnick.trading.beans.QueryOneElement;
import com.tesnick.trading.beans.Quote;
import com.tesnick.trading.beans.Results;
import com.tesnick.trading.dto.Period;
import com.tesnick.trading.dto.Ticker;
import com.tesnick.trading.utils.DateFormatter;
import com.tesnick.trading.utils.FileNamer;
import com.tesnick.trading.utils.ResultsMapper;
import com.tesnick.trading.utils.TickersReader;
import com.tesnick.trading.yahoo.YahooHarvester;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    protected final Log logger = LogFactory.getLog(getClass());

    private DateFormatter dateFormatter = new DateFormatter();
    private YahooHarvester yahooHarvester = new YahooHarvester();
    private TickersReader tickersReader = new TickersReader();
    private ResultsMapper resultsMapper = new ResultsMapper();

    public static void main(String[] args) throws IOException {

        DataManager dataManager = new DataManager();
        dataManager.getDataForAllTickers("forex");
    }

    public void getDataForAllTickers(String type) throws IOException {

        logger.info("Getting quotes from scratch...");

        List<Ticker> tickers = tickersReader.getTickers(new File("./src/main/resources/" + type + "-tickers.txt"));
        getData(tickers);
    }

    public void getDataForAllTickers() throws IOException {

        logger.info("Getting quotes from scratch...");

        List<Ticker> tickers = tickersReader.getTickers();
        getData(tickers);
    }

    private void getData(List<Ticker> tickers) throws IOException {

        for (Ticker ticker : tickers) {
            LocalDate oneyearAgo = LocalDate.now().minusYears(1);
            getDataByTicker(oneyearAgo, null, ticker);
        }
    }

    public void getDataByTicker(LocalDate fromDate, LocalDate toDate, Ticker ticker) throws IOException {

        String endDate;
        if (toDate == null) {
            endDate = "today";
        } else {
            endDate = toDate.format(dateFormatter.getFormatter());
        }

        logger.info("Getting quotes for " + ticker.getName() + " from " + fromDate.format(dateFormatter.getFormatter()) + " until today...");

        Query query = yahooHarvester.getData(ticker.getName(), fromDate.format(dateFormatter.getFormatter()), endDate);

        if (query != null) {
            // Serialize results
            String startDateString = query.getResults().getQuote().get(0).getDate();
            String endDateString = query.getResults().getQuote()
                    .get(query.getResults().getQuote().size() - 1).getDate();

            File outputFile = FileNamer.fromTickerAndDate(ticker.getName(),
                    startDateString, endDateString);

            resultsMapper.fromJavaToFile(query.getResults(), outputFile);

            logger.info("Data saved on file " + outputFile.getAbsolutePath());
        }
    }

    public void updateData() throws IOException {

        logger.info("Updating data...");

        File workingDirectory = new File("./data");
        for (File file : workingDirectory.listFiles()) {

            logger.info("Reading file " + file.getAbsolutePath() + "...");

            Results results = resultsMapper.fromFileToJava(file);

            System.out.println("There are " + results.getQuote().size()
                    + " quotes.");

            Period period = getPeriodToBeUpdated(results, file);

            logger.info("From file there are quotes from "
                    + period.getStartDate() + " to " + period.getEndDate());

            LocalDate localDate = LocalDate.parse(period.getEndDate(), dateFormatter.getFormatter());
            LocalDate getFromDate = localDate.plusDays(1);
            long days = java.time.Period.between(getFromDate, LocalDate.now()).getDays();

            if (days > 0) {

                logger.info("Retrieving quotes from " + dateFormatter.getFormatter().format(getFromDate) + " until today.. (" + days + " day/s" + ")");

                try {
                    Query query = yahooHarvester.getData(period.getSymbol(), dateFormatter.getFormatter().format(getFromDate), "today");

                    if (query != null) {
                        results.getQuote().addAll(query.getResults().getQuote());
                    }
                } catch (HttpMessageNotReadableException e) {
                    // Case API it's returning only one quote
                    QueryOneElement query = yahooHarvester.getDataForOneDay(
                            period.getSymbol(), dateFormatter.getFormatter()
                                    .format(getFromDate), "today");

                    if (query != null) {
                        results.getQuote().add(query.getResults().getQuote());
                    }
                }

                logger.info("Now there are " + results.getQuote().size() + " quotes.");

                String startDateString = results.getQuote().get(0).getDate();
                String endDateString = results.getQuote()
                        .get(results.getQuote().size() - 1).getDate();
                String ticker = results.getQuote().get(0).getSymbol();

                File outputFile = FileNamer.fromTickerAndDate(ticker,
                        startDateString, endDateString);

                resultsMapper.fromJavaToFile(results, outputFile);

                file.delete();
            }
        }
    }

    private Period getPeriodToBeUpdated(Results results, File file) {

        String startDate = null;
        String endDate = null;
        String symbol = null;
        if (results.getQuote().size() == 0) {
            throw new IllegalArgumentException("File " + file.getAbsolutePath()
                    + " is empty");
        } else if (results.getQuote().size() == 1) {
            throw new IllegalArgumentException("File " + file.getAbsolutePath()
                    + " has only one value (two minimum)");
        } else if (results.getQuote().size() > 1) {
            symbol = results.getQuote().get(0).getSymbol();

            Quote minDateQuoteOnFile = results.getQuote().get(0);
            startDate = minDateQuoteOnFile.getDate();

            Quote maxDateQuoteOnFile = results.getQuote().get(
                    results.getQuote().size() - 1);
            endDate = maxDateQuoteOnFile.getDate();

        }

        return new Period(startDate, endDate, symbol);
    }

    private List<Quote> filterLastOneYear(Results results) {

        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        logger.info("Using quotes from " + oneYearAgo);

        List<Quote> valuableQuotes = new ArrayList<Quote>();
        for (Quote quote : results.getQuote()) {
            LocalDate quoteDate = LocalDate.parse(quote.getDate(),
                    dateFormatter.getFormatter());
            // We are intested only on quotes from one year ago
            if (quoteDate.compareTo(oneYearAgo) > 0) {
                valuableQuotes.add(quote);
            }
        }
        return valuableQuotes;
    }
}
