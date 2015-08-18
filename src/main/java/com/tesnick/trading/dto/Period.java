package com.tesnick.trading.dto;

public class Period {
    private String startDate;

    private String endDate;

    private String symbol;

    public Period(String startDate, String endDate, String symbol) {
        super();
        this.startDate = startDate;
        this.endDate = endDate;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
