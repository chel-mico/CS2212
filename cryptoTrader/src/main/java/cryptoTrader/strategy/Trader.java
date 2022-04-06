package cryptoTrader.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cryptoTrader.utils.Broker;
import cryptoTrader.utils.DataFetcher;

public class Trader {
	private ArrayList<Broker> brokers; //list of brokers to be consulted
	private Map<String, Double> coinPrices; //saves prices for use throughout the trading process
	private DataFetcher dataFetcher;
	
	public Trader(ArrayList<Broker> brokers) {
		this.brokers = brokers;
		this.coinPrices = new HashMap<String, Double>();
		this.dataFetcher = new DataFetcher();
	}
	
	public Object[][] performTrade() {
		ArrayList<String[]> tradesListDynamic = new ArrayList<String[]>();
		for (Broker broker : brokers) {
			//fetch coins and prices and add to the currently saved prices
			for (String coin : broker.getCoins()) {
				coinPrices.put(coin, dataFetcher.getPriceForCoin(coin, "08-09-2021"));
			}
		}
		
		for(Broker broker : brokers) {
			//execute the appropriate strategy for each broker
			for (String coin : broker.getCoins()) {
				if (coinPrices.get(coin) == 0.0) {
					String[] trade = {
						broker.getName(),
						broker.getStrategy(),
						coin,
						"Fail"/*buy/sell/fail*/,
						"Null"/*quantity*/,
						"Null",
						"08-09-2021"
					};
					tradesListDynamic.add(trade);
					continue;
				}
				boolean failed = false;
				switch (broker.getStrategy()) {
				case "Strategy-A":
					//execute strategy A
					break;
				case "Strategy-B":
					//execute strategy B
					break;
				case "Strategy-C":
					//execute strategy C
					break;
				case "Strategy-D":
					//execute strategy D
					break;
				default:
					//failed trade
					break;
				}
				
				if (failed) {
					String[] trade = {
						broker.getName(),
						broker.getStrategy(),
						coin,
						"Fail",
						"Null",
						"Null",
						"08-09-2021"
					};
					tradesListDynamic.add(trade);
				} else {
					String[] trade = {
						broker.getName(),
						broker.getStrategy(),
						coin,
						""/*buy/sell*/,
						""/*quantity*/,
						coinPrices.get(coin).toString(),
						"08-09-2021"
					};
					tradesListDynamic.add(trade);
				}
			}
		}
		//send the data off to graph
		String[][] tradesList = new String[tradesListDynamic.size()][7];
		for(int i = 0; i < tradesListDynamic.size(); i++) {
			tradesList[i] = tradesListDynamic.get(i);
		}
		return tradesList;
	}
}
