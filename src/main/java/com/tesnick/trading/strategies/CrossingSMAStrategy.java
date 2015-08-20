package com.tesnick.trading.strategies;

import eu.verdelhan.ta4j.Rule;
import eu.verdelhan.ta4j.Strategy;
import eu.verdelhan.ta4j.TimeSeries;
import eu.verdelhan.ta4j.indicators.simple.ClosePriceIndicator;
import eu.verdelhan.ta4j.indicators.trackers.SMAIndicator;
import eu.verdelhan.ta4j.trading.rules.CrossedDownIndicatorRule;
import eu.verdelhan.ta4j.trading.rules.CrossedUpIndicatorRule;

public class CrossingSMAStrategy implements TesnickStrategy {

    private String name = "CrossingSMAStrategy";

    private Double profit;

    public Strategy buildStrategy(TimeSeries series) {

        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);

        SMAIndicator shortSma = new SMAIndicator(closePrice, 6);
        SMAIndicator longSma = new SMAIndicator(closePrice, 70);

        // Entry rule
        Rule entryRule = new CrossedUpIndicatorRule(shortSma, longSma);

        // Exit rule
        Rule exitRule = new CrossedDownIndicatorRule(shortSma, longSma);

        return new Strategy(entryRule, exitRule);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Double getProfit() {
        return profit;
    }

    @Override
    public void setProfit(Double profit) {
        this.profit = profit;
    }
}
