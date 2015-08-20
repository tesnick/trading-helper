package com.tesnick.trading.yahoo;

import com.tesnick.trading.beans.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.client.RestTemplate;

public class YahooHarvester {

    private final Log logger = LogFactory.getLog(getClass());

    private final String query = "https://query.yahooapis.com/v1/public/yql?q=select * from yahoo.finance.historicaldata where symbol = \"";
    private final String query2 = "| reverse()&format=json&diagnostics=false&env=store://datatables.org/alltableswithkeys";

    private final RestTemplate restTemplate = new RestTemplate();

    public Query getData(String ticker, String startDate, String endDate) {

        String finalQuery = query + ticker + "\" and startDate = \"" + startDate + "\" and endDate = \"" + endDate + "\"" + query2;

        logger.info("Executing query against -> " + finalQuery);

        YahooResult result = restTemplate.getForObject(finalQuery, YahooResult.class);

        logger.info("Received " + result.getQuery().getCount() + " results.");

        if (result.getQuery().getCount() > 0) {

            result.getQuery().getResults().getQuote().forEach(logger::debug);
            return result.getQuery();
        }

        return null;
    }

    public QueryOneElement getDataForOneDay(String ticker, String startDate, String endDate) {

        String finalQuery = query + ticker + "\" and startDate = \"" + startDate + "\" and endDate = \"" + endDate + "\"" + query2;

        logger.info("Executing query against -> " + finalQuery);

        YahooResultOneElement result = restTemplate.getForObject(finalQuery, YahooResultOneElement.class);

        logger.info("Received " + result.getQuery().getCount() + " results.");

        if (result.getQuery().getCount() > 0) {
            Quote quote = result.getQuery().getResults().getQuote();
            logger.debug(quote);

            return result.getQuery();
        }

        return null;
    }
}
