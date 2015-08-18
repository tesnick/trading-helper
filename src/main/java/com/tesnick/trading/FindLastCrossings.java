package com.tesnick.trading;

import com.tesnick.trading.beans.Results;
import com.tesnick.trading.dto.CrossingValue;
import com.tesnick.trading.dto.CrossingValueComparator;
import com.tesnick.trading.dto.EnhancedData;
import com.tesnick.trading.dto.Tendency;
import com.tesnick.trading.utils.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindLastCrossings {

    protected final Log logger = LogFactory.getLog(getClass());

    private ResultsMapper resultsMapper = new ResultsMapper();
    private YahooMapper yahooMapper = new YahooMapper();
    private CrossFinder crossFinder = new CrossFinder();
    private DateFormatter dateFormatter = new DateFormatter();
    private StockFinder stockFinder = new StockFinder();
    private PercentageDiff percentageDiff = new PercentageDiff();
    private static DataManager application = new DataManager();

    public static void main(String[] args) throws IOException, ParseException {

        application.updateData();

        FindLastCrossings findLastCrossings = new FindLastCrossings();

        List<CrossingValue> sortedCrossings = findLastCrossings.returnCrossingsAlcistasByFolder();

        Collections.sort(sortedCrossings, new CrossingValueComparator());

        for (CrossingValue crossing : sortedCrossings) {

            System.out.println("Crossing -> " + crossing);
        }
    }

    public List<CrossingValue> returnCrossingsAlcistasByFolder() throws IOException, ParseException {

        List<CrossingValue> crossings = new ArrayList<CrossingValue>();
        int numberOfCompanies = 0;
        for (File file : new File("./data").listFiles()) {

            logger.debug("Reading file " + file.getName() + "...");
            Results results = resultsMapper.fromFileToJava(file);

            logger.debug("Enhancing stock values info with mm6 and mm70...");
            EnhancedData data = yahooMapper.convert(results);

            logger.debug("Locking for crossing points between mm60 and mm70 for company "
                    + data.getTicker() + "...");

            List<CrossingValue> allCrossings = crossFinder.findCrossings(data);

            logger.info("There are " + allCrossings.size()
                    + " crossing candidates. Analyzing...");

            if (!allCrossings.isEmpty()) {
                // get last cross
                CrossingValue lastCross = allCrossings.get(allCrossings.size() - 1);
                if ((lastCross.getM6Tendency() == Tendency.ALCISTA
                        && lastCross.getM70Tendency() == Tendency.ALCISTA) ||
                        (lastCross.getM6Tendency() == Tendency.BAJISTA
                                && lastCross.getM70Tendency() == Tendency.BAJISTA)) {

                    // Check if difference between
                    // (current stock value <<-->> crossing) < 3%
                    String symbol = lastCross.getTicker();

                    Double lastValue = Double.parseDouble(stockFinder
                            .getLastValue(symbol, data));

                    if (percentageDiff
                            .isMinorOfPercentage(lastCross, lastValue)) {

                        long days = ChronoUnit.DAYS.between(LocalDate.parse(
                                lastCross.getDate(),
                                dateFormatter.getFormatter()), LocalDate.now());

                        if (days < 7) {

                            // check pending moving averages
                            double pendingM6 = PendingCalculator.getPendent(lastCross.getM6OldValue(), lastCross.getM6NewValue(), lastCross.getOldDate(), lastCross.getDate());
                            double pendingM70 = PendingCalculator.getPendent(lastCross.getM70OldValue(), lastCross.getM70NewValue(), lastCross.getOldDate(), lastCross.getDate());

                            if (Math.abs(pendingM6) > 0.1 && Math.abs(pendingM70) > 0.1) {
                                //System.out.println("VALID symbol -> " + symbol + ", pendingM6 -> " + pendingM6);
                                //System.out.println("VALID symbol -> " + symbol + ", pendingM70 -> " + pendingM70);

                                crossings.add(lastCross);

                            } else {
                                //System.out.println("INVALID symbol -> " + symbol + ", pendingM6 -> " + pendingM6);
                                //System.out.println("INVALID symbol -> " + symbol + ", pendingM70 -> " + pendingM70);
                            }
                        }
                    }
                }
            }

            logger.info("Analysis for " + data.getTicker() + " finished.");
            numberOfCompanies++;
        }

        logger.info("Analysis for " + numberOfCompanies + " companies done. Found " + crossings.size() + " candidate crossings.");

        return crossings;
    }
}
