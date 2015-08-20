package com.tesnick.trading.utils;

import com.tesnick.trading.dto.CrossingValue;
import com.tesnick.trading.dto.EnhancedData;
import com.tesnick.trading.dto.EnhancedQuote;
import com.tesnick.trading.dto.Tendency;

import java.util.ArrayList;
import java.util.List;

public class CrossFinder {

    public List<CrossingValue> findCrossings(EnhancedData enhancedData) {

        List<CrossingValue> crossings = new ArrayList<>();

        double m6OldValue = enhancedData.getQuotes().get(0).getMa6();
        double m70OldValue = enhancedData.getQuotes().get(0).getMa70();
        String dateOld = enhancedData.getQuotes().get(0).getDate();
        for (int i = 1; i < enhancedData.getQuotes().size(); i++) {

            EnhancedQuote enhancedQuote = enhancedData.getQuotes().get(i);

            double m6NewValue = enhancedQuote.getMa6();
            double m70NewValue = enhancedQuote.getMa70();
            String dateNew = enhancedQuote.getDate();
            if (areCrossed(m6OldValue, m70OldValue, m6NewValue, m70NewValue)) {

                CrossingValue crossingValue = new CrossingValue(dateNew, dateOld, m6OldValue, m70OldValue,
                        m6NewValue, m70NewValue);

                crossingValue.setTicker(enhancedQuote.getSymbol());
                crossingValue.setM6Tendency(findTendency(m6OldValue, m6NewValue));
                crossingValue.setM70Tendency(findTendency(m70OldValue, m70NewValue));

                crossings.add(crossingValue);
            }

            m6OldValue = m6NewValue;
            m70OldValue = m70NewValue;
            dateOld = dateNew;
        }

        return crossings;
    }

    private Tendency findTendency(double oldValue, double newValue) {
        if (oldValue == newValue) {
            return Tendency.NO_TENDENCY;
        } else if (oldValue < newValue) {
            return Tendency.BULLISH;
        } else {
            return Tendency.BEARISH;
        }
    }

    public boolean areCrossed(double m6OldValue1, double m70OldValue2,
                              double m6NewValue1, double m70NewValue2) {

        boolean firstBiggerOld = false;
        if (m6OldValue1 > m70OldValue2) {
            firstBiggerOld = true;
        }

        boolean firstBiggerNew = false;
        if (m6NewValue1 > m70NewValue2) {
            firstBiggerNew = true;
        }

        return firstBiggerOld != firstBiggerNew;
    }
}
