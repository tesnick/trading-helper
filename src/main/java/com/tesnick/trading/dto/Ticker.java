package com.tesnick.trading.dto;

public class Ticker {

    private final String name;

    public Ticker(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Ticker [name=" + name + "]";
    }

}
