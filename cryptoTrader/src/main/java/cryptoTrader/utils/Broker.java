package cryptoTrader.utils;

import java.util.List;

import cryptoTrader.strategy.Strategy;

/**
 * A class representing a Broker, visualized as a row in the program
 * @author Group 34
 *
 */
public class Broker {
	
	// Instantiate variables
	private String name;
	private String[] coins;
	private Strategy strategy;
	
	/**
	 * Constructor function
	 * @param name The name of the Broker
	 * @param coins The coins the Broker is interested in
	 * @param strategy The strategy the Broker selected
	 */
	public Broker(String name, String[] coins, Strategy strategy) {
		this.name = name;
		this.coins = coins;
		this.strategy = strategy;
	}
	
	/**
	 * Accessor method to get the name of the Broker
	 * @return The name of the Broker
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Accessor method to get the coin list
	 * @return The coins the Broker is interested in
	 */
	public String[] getCoins() {
		return coins;
	}
	
	/**
	 * Accessor method to get the Broker's strategy
	 * @return The strategy the Broker chose
	 */
	public Strategy getStrategy() {
		return strategy;
	}
	
	public String getStrategyName() {
		return strategy.getName();
	}
}
