package cryptoTrader.strategy;

import java.util.Map;

/**
 * Abstract class for trading strategies (A,B,C,D)
 * Helps implement the Factory design pattern
 */


public abstract class Strategy {
	protected String name; 
	protected String[] coins;
	protected String purchasedCoin;
	
    public Strategy (String name, String[] coins, String purchasedCoin) {
    	this.name = name;
    	this.coins = coins;
    	this.purchasedCoin = purchasedCoin;
    }
    
    public abstract Map<String, Integer> executeStrategy(Map<String, Double> prices);
    
    public String getName() {
    	return name;
    }
    
    public String[] getCoins() {
    	return coins;
    }

	public String getPurchasedCoin() {
		return purchasedCoin;
	}
}