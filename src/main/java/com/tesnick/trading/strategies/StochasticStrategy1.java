package com.tesnick.trading.strategies;

import eu.verdelhan.ta4j.Rule;
import eu.verdelhan.ta4j.Strategy;
import eu.verdelhan.ta4j.TimeSeries;
import eu.verdelhan.ta4j.indicators.oscillators.StochasticOscillatorKIndicator;
import eu.verdelhan.ta4j.indicators.trackers.SMAIndicator;
import eu.verdelhan.ta4j.trading.rules.CrossedDownIndicatorRule;
import eu.verdelhan.ta4j.trading.rules.CrossedUpIndicatorRule;

/**
 * http://es.wikipedia.org/wiki/Oscilador_estoc%C3%A1stico
 */
public class StochasticStrategy1 implements TesnickStrategy {

    private String name = "StochasticStrategy1";

    private Double profit;

    public Strategy buildStrategy(TimeSeries series) {

        StochasticOscillatorKIndicator sof = new StochasticOscillatorKIndicator(series, 14);
        SMAIndicator sma = new SMAIndicator(sof, 3);

        // Entry rule
        Rule entryRule = new CrossedUpIndicatorRule(sof, sma);

        // Exit rule
        Rule exitRule = new CrossedDownIndicatorRule(sof, sma);

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
