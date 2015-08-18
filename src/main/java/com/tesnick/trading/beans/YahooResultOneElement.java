package com.tesnick.trading.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YahooResultOneElement {

    private QueryOneElement query;

    public QueryOneElement getQuery() {
        return query;
    }

    public void setQuery(QueryOneElement query) {
        this.query = query;
    }

}
