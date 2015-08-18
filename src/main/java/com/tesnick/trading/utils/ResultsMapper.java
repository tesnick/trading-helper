package com.tesnick.trading.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tesnick.trading.beans.Results;
import com.tesnick.trading.beans.YahooResults;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class ResultsMapper {

    private ObjectMapper mapper = new ObjectMapper();

    public Results fromFileToJava(File file) throws IOException {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        return mapper.readValue(file, Results.class);
    }

    public void fromJavaToFile(Results results, File outputFile)
            throws IOException {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        mapper.writeValue(outputFile, results);
    }

    public YahooResults fromFileToJavaYahooApi(File file) throws IOException {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        return mapper.readValue(file, YahooResults.class);
    }

    public void fromJavaToFileYahooApi(YahooResults results, File outputFile)
            throws IOException {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);

        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }

        Collections.sort(results.getHistoricalQuotes(), new QuotesComparator());
        mapper.writeValue(outputFile, results);
    }
}
