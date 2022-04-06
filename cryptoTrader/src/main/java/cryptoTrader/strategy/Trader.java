package cryptoTrader.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cryptoTrader.utils.Broker;

public class Trader {
	private ArrayList<Broker> brokers; //list of brokers to be consulted
	private Map<String, Double> coinPrices; //saves prices for use throughout the trading process
	
	public Trader(ArrayList<Broker> brokers) {
		this.brokers = brokers;
		this.coinPrices = new HashMap<String, Double>();
	}
	
	public void performTrade() {
		for (Broker broker : brokers) {
			//fetch coins and prices and add to the currently saved prices
			String coinList = "";
			for (String coin : broker.getCoins()) {
				coinList += coin + ",";
			}
			//consult each trader with their strategy
			
			//send off data
		}
	}
}
