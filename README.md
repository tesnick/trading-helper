# trading-helper

A library to help in trading decisions.

----------

With trading-helper you can:

1. **Get stock data using Yahoo YQL API and store it:** The library has a rest-client that executes requests to [Yahoo YQL API](https://developer.yahoo.com/yql) in order to get stock data. More concretely, it
gets the info from the *yahoo.finance.historicaldata* community table. For more info about community tables [click here](http://www.datatables.org/) and the list of all community tables can be found [here](https://developer.yahoo.com/yql/console/?q=show%20tables&env=store://datatables.org/alltableswithkeys).

2. **Update anytime:** You can download stock data and update it anytime you want.
3. **Create strategies and validate them:** Thanks to the [ta4j](https://github.com/mdeverdelhan/ta4j) library, trading-helper can use the downloaded data and implement strategies, getting useful results as the profit compared to a buy-and-hold strategy, for example.
4. **Get best candidates using the moving average crossover:** It can process all the stocks downloaded on a directory and find the best candidates to enter in short/long using the classic moving average crossover strategy. I've added some improvements, for example
checking if the time between the cross and today is short, if the value itself is near the crossing value (it still makes sense to enter) and check the gradient of the moving average.

