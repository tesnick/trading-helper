package com.tesnick.trading.utils;

import com.tesnick.trading.beans.Query;
import com.tesnick.trading.yahoo.YahooHarvester;
import org.junit.Ignore;
import org.junit.Test;

public class YahooHarvesterTest {

    private YahooHarvester harvester = new YahooHarvester();

    @Test
    @Ignore
    public void getDataTest() {
        Query query = harvester.getData("ABE.MC", "2014-01-01", "today");
        System.out.println(query.getCount());
    }

}
