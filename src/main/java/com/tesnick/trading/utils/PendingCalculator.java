package com.tesnick.trading.utils;

import java.text.ParseException;

public class PendingCalculator {

    public static double getPendent(double m6OldValue, double m6NewValue, String oldDate, String date) throws ParseException {

        double y2 = m6NewValue;
        double y1 = m6OldValue;
        //double x2 = dateLong;
        //double x1 = oldDateLong;

        // The difference between x1-x2 is 1 day
        return (y2 - y1) / 1;

    }
}
