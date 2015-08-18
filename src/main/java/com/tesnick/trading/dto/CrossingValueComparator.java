package com.tesnick.trading.dto;

import com.tesnick.trading.utils.DateFormatter;

import java.time.LocalDate;
import java.util.Comparator;

public class CrossingValueComparator implements Comparator<CrossingValue> {

    private DateFormatter formatter = new DateFormatter();

    @Override
    public int compare(CrossingValue o1, CrossingValue o2) {
        LocalDate date1 = LocalDate.parse(o1.getDate(),
                formatter.getFormatter());
        LocalDate date2 = LocalDate.parse(o2.getDate(),
                formatter.getFormatter());
        return date2.compareTo(date1);
    }
}
