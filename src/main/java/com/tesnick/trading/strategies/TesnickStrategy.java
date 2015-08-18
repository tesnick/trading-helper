package com.tesnick.trading.strategies;

import eu.verdelhan.ta4j.Strategy;
import eu.verdelhan.ta4j.TimeSeries;

/**
 * Created by tesnick on 6/06/15.
 */
public interface TesnickStrategy {

    Strategy buildStrategy(TimeSeries series);

    String getName();

    void setProfit(Double profit);

    Double getProfit();

}
