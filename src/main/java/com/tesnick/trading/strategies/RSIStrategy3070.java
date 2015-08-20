package com.tesnick.trading.strategies;

import eu.verdelhan.ta4j.Decimal;
import eu.verdelhan.ta4j.Rule;
import eu.verdelhan.ta4j.Strategy;
import eu.verdelhan.ta4j.TimeSeries;
import eu.verdelhan.ta4j.indicators.simple.ClosePriceIndicator;
import eu.verdelhan.ta4j.indicators.trackers.RSIIndicator;
import eu.verdelhan.ta4j.trading.rules.OverIndicatorRule;
import eu.verdelhan.ta4j.trading.rules.UnderIndicatorRule;

/**
 * http://es.wikipedia.org/wiki/%C3%8Dndice_RSI
 */
public class RSIStrategy3070 implements TesnickStrategy {

    private String name = "RSIStrategy3070";

    private Double profit;

    public Strategy buildStrategy(TimeSeries series) {

        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);

        RSIIndicator rsiIndicator = new RSIIndicator(closePrice, 14);

        // Entry rule
        Rule entryRule = new UnderIndicatorRule(rsiIndicator, Decimal.valueOf(30));

        // Exit rule
        Rule exitRule = new OverIndicatorRule(rsiIndicator, Decimal.valueOf(70));

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
