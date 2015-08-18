package com.tesnick.trading;

import com.tesnick.trading.dto.Ticker;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;

public class DataManagerTest {

    private DataManager application = new DataManager();

    @Test
    @Ignore
    public void getDataForAllTickersTest() throws IOException {
        application.getDataForAllTickers();
    }

    @Test
    @Ignore
    public void updateDataTest() throws IOException {
        application.updateData();
    }

    @Test
    @Ignore
    public void getAllDataForATicker() throws IOException {

        String ticker = "A3M.MC";
        LocalDate from = LocalDate.now().minusYears(1).minusMonths(6);
        application.getDataByTicker(from, null, new Ticker(ticker));
    }
}
