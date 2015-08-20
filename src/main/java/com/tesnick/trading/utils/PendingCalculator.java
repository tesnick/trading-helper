package com.tesnick.trading.utils;

public class PendingCalculator {

    public static double getPendent(double m6OldValue, double m6NewValue) {

        // The difference between x1-x2 is 1 day
        return (m6NewValue - m6OldValue) / 1;
    }
}
