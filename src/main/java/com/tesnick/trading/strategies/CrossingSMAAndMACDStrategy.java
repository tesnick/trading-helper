package com.tesnick.trading.strategies;

import eu.verdelhan.ta4j.Decimal;
import eu.verdelhan.ta4j.Rule;
import eu.verdelhan.ta4j.Strategy;
import eu.verdelhan.ta4j.TimeSeries;
import eu.verdelhan.ta4j.indicators.simple.ClosePriceIndicator;
import eu.verdelhan.ta4j.indicators.trackers.MACDIndicator;
import eu.verdelhan.ta4j.indicators.trackers.SMAIndicator;
import eu.verdelhan.ta4j.trading.rules.CrossedUpIndicatorRule;
import eu.verdelhan.ta4j.trading.rules.OverIndicatorRule;

/**
 * Created by tesnick on 6/06/15.
 */
public class CrossingSMAAndMACDStrategy implements TesnickStrategy {

    private String name = "CrossingSMAAndMACDStrategy";

    private Double profit;

    public Strategy buildStrategy(TimeSeries series) {

        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);

        SMAIndicator shortSma = new SMAIndicator(closePrice, 6);
        SMAIndicator longSma = new SMAIndicator(closePrice, 70);
        MACDIndicator macd = new MACDIndicator(closePrice, 9, 26);

        // Entry rule
        Rule entryRule = new CrossedUpIndicatorRule(shortSma, longSma);

        // Exit rule
        Rule exitRule = new OverIndicatorRule(macd, Decimal.valueOf(0.60));

        return new Strategy(entryRule, exitRule);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setProfit(Double profit) {
        this.profit = profit;
    }

    @Override
    public Double getProfit() {
        return profit;
    }
}
