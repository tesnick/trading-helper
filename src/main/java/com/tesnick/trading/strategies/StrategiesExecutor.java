package com.tesnick.trading.strategies;

import com.tesnick.trading.beans.YahooResults;
import com.tesnick.trading.utils.DateFilter;
import com.tesnick.trading.utils.FileNamer;
import com.tesnick.trading.utils.HistoricalQuotesMapper;
import com.tesnick.trading.utils.ResultsMapper;
import eu.verdelhan.ta4j.*;
import eu.verdelhan.ta4j.analysis.criteria.TotalProfitCriterion;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class StrategiesExecutor {

    private static ResultsMapper resultsMapper = new ResultsMapper();

    protected final Log logger = LogFactory.getLog(getClass());

    private static DateFilter dateFilter = new DateFilter();

    public static void main(String[] args) throws IOException {
        StrategiesExecutor findBestStrategy = new StrategiesExecutor();
        findBestStrategy.find("TRE.MC");
    }

    // Indicators:

    // 2) Parabolic SAR
    // 7) Bollinger Bands

    private double executeStrategy(TimeSeries series, Strategy strategy, String name) {

        // Running the strategy
        TradingRecord tradingRecord = series.run(strategy);

        logger.info("Number of trades for " + name + " strategy: " + tradingRecord.getTradeCount());

        for (Trade trade : tradingRecord.getTrades()) {
            Order entryOrder = trade.getEntry();
            Tick entryTick = series.getTick(entryOrder.getIndex());
            logger.info("   Entry order, TICK: " + entryTick);

            Order exitOrder = trade.getExit();
            Tick exitTick = series.getTick(exitOrder.getIndex());
            logger.info("   Exit order, TICK: " + exitTick);

            logger.info("       Difference: " + (exitTick.getClosePrice().minus(entryTick.getClosePrice())));
        }

        return new TotalProfitCriterion().calculate(series, tradingRecord);

    }

    public void find(String ticker) throws IOException {

        List<TesnickStrategy> strategies = prepareStrategies();

        // get file
        File dataFile = FileNamer.getFileFromYahooDataFolder(ticker);

        // update data
        //dataManagerForYahooAPI.updateData(dataFile);

        logger.info("Loaded data for '" + ticker + "' from " + dataFile.getAbsolutePath());

        // load data
        YahooResults yahooResults = resultsMapper.fromFileToJavaYahooApi(dataFile);

        Calendar dateStart = new GregorianCalendar(2013, 5, 7);
        Calendar dateEnd = new GregorianCalendar(2015, 5, 5);
        dateFilter.filter(yahooResults, dateStart,dateEnd);

        // convert to timeSeries object
        TimeSeries series = HistoricalQuotesMapper.fromHistoricalQuoteToSeries(yahooResults.getHistoricalQuotes());

        for(TesnickStrategy tesnickStrategy : strategies){

            double currentProfitStrategy = executeStrategy(series, tesnickStrategy.buildStrategy(series), tesnickStrategy.getName());
            tesnickStrategy.setProfit(currentProfitStrategy);

        }

        Collections.sort(strategies, (o1, o2) -> o2.getProfit().compareTo(o1.getProfit()));
        for(TesnickStrategy tesnickStrategy : strategies){

            logger.info("Strategy " + tesnickStrategy.getName() + " has this profit -> " + tesnickStrategy.getProfit());
        }

    }

    private List<TesnickStrategy> prepareStrategies() {

        List<TesnickStrategy> strategies = new ArrayList<>();
        strategies.add(new CrossingSMAAndMACDStrategy());
        strategies.add(new CrossingSMAStrategy());
        strategies.add(new MACDStrategy());
        strategies.add(new MomentumCombination());
        strategies.add(new RoCStrategy());
        strategies.add(new RSIStrategy2080());
        strategies.add(new RSIStrategy3070());
        strategies.add(new StochasticStrategy1());
        strategies.add(new StochasticStrategy2());
        strategies.add(new StochasticStrategy3());
        strategies.add(new WilliamsStrategy());

        return  strategies;
    }

}
