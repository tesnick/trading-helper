package com.tesnick.trading.utils;

import com.tesnick.trading.beans.Results;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ResultsMapperTest {

    private ResultsMapper resultsSerializer = new ResultsMapper();

    @Test
    @Ignore
    public void fromFileToJava() throws IOException {

        File file = new File("./data/stock_ABE.MC_2015-05-01_today.json");
        Results results = resultsSerializer.fromFileToJava(file);

        Assert.assertEquals(9, results.getQuote().size());
    }

}
