package com.tesnick.trading.utils;

import com.tesnick.trading.dto.CrossingValue;

public class PercentageDiffGetter {

    public boolean isMinorOfPercentage(CrossingValue crossingValue,
                                       Double lastValue) {

        double difference1 = calculateDifference(crossingValue.getM6OldValue(), lastValue);

        double difference2 = calculateDifference(crossingValue.getM70OldValue(), lastValue);

        double difference3 = calculateDifference(crossingValue.getM6NewValue(), lastValue);

        double difference4 = calculateDifference(crossingValue.getM70NewValue(), lastValue);

        return difference1 < 3 || difference2 < 3 || difference3 < 3 || difference4 < 3;

    }

    private double calculateDifference(double oldValue, Double newValue) {
        return ((Math.abs((newValue - oldValue)) / oldValue) * 100);
    }
}
