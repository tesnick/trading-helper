package com.tesnick.trading.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Quote {

    private String Low;

    private String Open;

    private String Adj_Close;

    private String Close;

    private String Date;

    private String Volume;

    private String Symbol;

    private String High;

    @JsonProperty("Low")
    public String getLow() {
        return Low;
    }

    public void setLow(String Low) {
        this.Low = Low;
    }

    @JsonProperty("Open")
    public String getOpen() {
        return Open;
    }

    public void setOpen(String Open) {
        this.Open = Open;
    }

    @JsonProperty("Adj_Close")
    public String getAdj_Close() {
        return Adj_Close;
    }

    public void setAdj_Close(String Adj_Close) {
        this.Adj_Close = Adj_Close;
    }

    @JsonProperty("Close")
    public String getClose() {
        return Close;
    }

    public void setClose(String Close) {
        this.Close = Close;
    }

    @JsonProperty("Date")
    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    @JsonProperty("Volume")
    public String getVolume() {
        return Volume;
    }

    public void setVolume(String Volume) {
        this.Volume = Volume;
    }

    @JsonProperty("Symbol")
    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String Symbol) {
        this.Symbol = Symbol;
    }

    @JsonProperty("High")
    public String getHigh() {
        return High;
    }

    public void setHigh(String High) {
        this.High = High;
    }

    @Override
    public String toString() {
        return "Quote [Adj_Close=" + Adj_Close + ", Close=" + Close + ", Date="
                + Date + ", Volume=" + Volume + ", Symbol=" + Symbol + "]";
    }

}
