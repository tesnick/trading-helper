package com.tesnick.trading.dto;

import com.tesnick.trading.beans.Quote;

public class EnhancedQuote extends Quote {

    private double ma6;

    private double ma70;

    private double ma200;

    public double getMa6() {
        return ma6;
    }

    public void setMa6(double ma6) {
        this.ma6 = ma6;
    }

    public double getMa70() {
        return ma70;
    }

    public void setMa70(double ma70) {
        this.ma70 = ma70;
    }

    public double getMa200() {
        return ma200;
    }

    public void setMa200(double ma200) {
        this.ma200 = ma200;
    }

}
