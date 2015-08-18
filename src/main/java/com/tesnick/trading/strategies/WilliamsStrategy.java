package com.tesnick.trading.strategies;

import eu.verdelhan.ta4j.Decimal;
import eu.verdelhan.ta4j.Rule;
import eu.verdelhan.ta4j.Strategy;
import eu.verdelhan.ta4j.TimeSeries;
import eu.verdelhan.ta4j.indicators.trackers.WilliamsRIndicator;
import eu.verdelhan.ta4j.trading.rules.OverIndicatorRule;
import eu.verdelhan.ta4j.trading.rules.UnderIndicatorRule;

/**
 * Created by tesnick on 6/06/15.
 */
public class WilliamsStrategy implements TesnickStrategy {

    private String name = "WilliamsStrategy";

    private double profit;

    public Strategy buildStrategy(TimeSeries series) {

        WilliamsRIndicator williamsRIndicator = new WilliamsRIndicator(series, 14);

        // Entry rule
        Rule entryRule = new OverIndicatorRule(williamsRIndicator, Decimal.valueOf(-80));

        // Exit rule
        Rule exitRule = new UnderIndicatorRule(williamsRIndicator, Decimal.valueOf(-20));

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
