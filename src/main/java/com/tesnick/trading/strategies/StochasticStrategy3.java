package com.tesnick.trading.strategies;

import eu.verdelhan.ta4j.Rule;
import eu.verdelhan.ta4j.Strategy;
import eu.verdelhan.ta4j.TimeSeries;
import eu.verdelhan.ta4j.indicators.oscillators.StochasticOscillatorDIndicator;
import eu.verdelhan.ta4j.indicators.oscillators.StochasticOscillatorKIndicator;
import eu.verdelhan.ta4j.trading.rules.CrossedDownIndicatorRule;
import eu.verdelhan.ta4j.trading.rules.CrossedUpIndicatorRule;

/**
 * http://ciberconta.unizar.es/leccion/fin005/570.HTM
 */
public class StochasticStrategy3 implements TesnickStrategy {

    private String name = "StochasticStrategy3";

    private Double profit;

    public Strategy buildStrategy(TimeSeries series) {

        StochasticOscillatorKIndicator sof = new StochasticOscillatorKIndicator(series, 14);
        StochasticOscillatorDIndicator sos = new StochasticOscillatorDIndicator(sof);

        // Entry rule
        Rule entryRule = new CrossedUpIndicatorRule(sos, sof);

        // Exit rule
        Rule exitRule = new CrossedDownIndicatorRule(sos, sof);

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
