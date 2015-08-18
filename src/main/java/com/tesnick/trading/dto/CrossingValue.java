package com.tesnick.trading.dto;

public class CrossingValue {

    private String oldDate;

    private String date;

    private double m6OldValue;

    private double m70OldValue;

    private double m6NewValue;

    private double m70NewValue;

    private Tendency m6Tendency;

    private Tendency m70Tendency;

    private String ticker;

    public CrossingValue(String date, String oldDate, double m6OldValue, double m70OldValue,
                         double m6NewValue, double m70NewValue) {

        super();

        this.date = date;
        this.oldDate = oldDate;

        this.m6OldValue = m6OldValue;
        this.m6NewValue = m6NewValue;

        this.m70OldValue = m70OldValue;
        this.m70NewValue = m70NewValue;
    }

    public Tendency getM6Tendency() {
        return m6Tendency;
    }

    public void setM6Tendency(Tendency m6Tendency) {
        this.m6Tendency = m6Tendency;
    }

    public Tendency getM70Tendency() {
        return m70Tendency;
    }

    public void setM70Tendency(Tendency m70Tendency) {
        this.m70Tendency = m70Tendency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getM6OldValue() {
        return m6OldValue;
    }

    public void setM6OldValue(double m6OldValue) {
        this.m6OldValue = m6OldValue;
    }

    public double getM70OldValue() {
        return m70OldValue;
    }

    public void setM70OldValue(double m70OldValue) {
        this.m70OldValue = m70OldValue;
    }

    public double getM6NewValue() {
        return m6NewValue;
    }

    public void setM6NewValue(double m6NewValue) {
        this.m6NewValue = m6NewValue;
    }

    public double getM70NewValue() {
        return m70NewValue;
    }

    public void setM70NewValue(double m70NewValue) {
        this.m70NewValue = m70NewValue;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getOldDate() {
        return oldDate;
    }

    public void setOldDate(String oldDate) {
        this.oldDate = oldDate;
    }

    @Override
    public String toString() {
        return "CrossingValue{" +
                "ticker='" + ticker + '\'' +
                ", m6Tendency=" + m6Tendency +
                ", m70Tendency=" + m70Tendency +
                "oldDate='" + oldDate + '\'' +
                ", date='" + date + '\'' +
                ", m6OldValue=" + m6OldValue +
                ", m70OldValue=" + m70OldValue +
                ", m6NewValue=" + m6NewValue +
                ", m70NewValue=" + m70NewValue +
                '}';
    }
}
