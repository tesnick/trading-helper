package com.tesnick.trading.strategies;

import eu.verdelhan.ta4j.Strategy;
import eu.verdelhan.ta4j.TimeSeries;

public interface TesnickStrategy {

    Strategy buildStrategy(TimeSeries series);

    String getName();

    Double getProfit();

    void setProfit(Double profit);

}
