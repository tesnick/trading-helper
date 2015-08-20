package com.tesnick.trading.utils;

import com.tesnick.trading.dto.EnhancedData;

public class StockFinder {

    public String getLastValue(EnhancedData data) {

        return data.getQuotes().get(data.getQuotes().size() - 1).getClose();

    }

}
