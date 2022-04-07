package cryptoTrader.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cryptoTrader.utils.Broker;
import cryptoTrader.utils.DataFetcher;

/**
 * Trader class
 */
public class Trader {
	private ArrayList<Broker> brokers; //list of brokers to be consulted
	private Map<String, Double> coinPrices; //saves prices for use throughout the trading process
	private DataFetcher dataFetcher;
	private String date;

	/**
	 * Trader constructor
	 * @param brokers
	 *
	 */
	public Trader(ArrayList<Broker> brokers) {
		this.brokers = brokers;
		this.coinPrices = new HashMap<String, Double>();
		this.dataFetcher = new DataFetcher();
		this.date = "06-04-2022"; //date format is dd-mm-yyyy
	}
	
	public Object[][] performTrade() {
		ArrayList<String[]> tradesListDynamic = new ArrayList<String[]>();
		for (Broker broker : brokers) {
			//fetch coins and prices and add to the currently saved prices
			for (String coin : broker.getCoins()) {
				coinPrices.put(coin, dataFetcher.getPriceForCoin(coin, date));
			}
		}
		
		for(Broker broker : brokers) {
			//execute the appropriate strategy for each broker
			for (String coin : broker.getCoins()) {
				if (coinPrices.get(coin) == 0.0) {
					String[] trade = {
						broker.getName(),
						broker.getStrategyName(),
						coin,
						"Fail"/*buy/sell/fail*/,
						"Null"/*quantity*/,
						"Null",
						date
					};
					tradesListDynamic.add(trade);
					continue;
				}
			}
			Map<String, Double> neededPrices = new HashMap<String, Double>();
			Map<String, Integer> result;
			switch (broker.getStrategyName()) {
			case "Strategy-A":
				neededPrices.put("bitcoin", coinPrices.get("bitcoin"));
				neededPrices.put("cardano", coinPrices.get("cardano"));
				result = broker.getStrategy().executeStrategy(neededPrices);
				break;
			case "Strategy-B":
				neededPrices.put("bitcoin", coinPrices.get("bitcoin"));
				result = broker.getStrategy().executeStrategy(neededPrices);
				break;
			case "Strategy-C":
				neededPrices.put("ethereum", coinPrices.get("ethereum"));
				neededPrices.put("dogecoin", coinPrices.get("dogecoin"));
				result = broker.getStrategy().executeStrategy(neededPrices);
				break;
			case "Strategy-D":
				neededPrices.put("ethereum", coinPrices.get("ethereum"));
				result = broker.getStrategy().executeStrategy(neededPrices);
				break;
			default:
				result = new HashMap<String, Integer>();
				result.put("Fail", 0);
				break;
			}

			//null returns
			if (result.containsKey("Fail")) {
				String[] trade = {
					broker.getName(),
					broker.getStrategyName(),
					broker.getStrategy().getPurchasedCoin(),
					"Fail",
					"Null",
					"Null",
					date
				};
				tradesListDynamic.add(trade);
			} else {
				String buyOrSell = result.containsKey("Buy") ? "Buy" : "Sell";
				String[] trade = {
					broker.getName(),
					broker.getStrategyName(),
					broker.getStrategy().getPurchasedCoin(),
					buyOrSell,
					result.get(buyOrSell).toString()/*quantity*/,
					coinPrices.get(broker.getStrategy().getPurchasedCoin()).toString(),
					date
				};
				tradesListDynamic.add(trade);
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
