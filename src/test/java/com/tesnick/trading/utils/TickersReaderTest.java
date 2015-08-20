package com.tesnick.trading.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class TickersReaderTest {

    private final TickersReader reader = new TickersReader();

    @Test
    public void getTickersTest() throws IOException {
        File file = new File("./src/main/resources", "aus-tickers.txt");
        Assert.assertEquals(27, reader.getTickers(file).size());
    }
}
