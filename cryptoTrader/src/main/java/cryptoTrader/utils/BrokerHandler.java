package cryptoTrader.utils;

import java.util.ArrayList;
import java.util.Arrays;

import cryptoTrader.strategy.StrategyFactory;

/**
 * A list that holds all the Broker information
 * @author Group 34
 *
 */
public class BrokerHandler {

	// ArrayList to hold all the brokers
	private ArrayList<Broker> brokers;
	
	/**
	 * Constructor function
	 */
	public BrokerHandler() {
		brokers = new ArrayList<Broker>();
	}
	
	/**
	 * Adds a Broker to the ArrayList
	 * @param name The name of the broker
	 * @param coins The coins the broker is interested in
	 * @param strategy The strategy they chose
	 * @return
	 */
	public boolean addBroker(String name, Object[] coins, String strategy) {
		// Check if broker already exists
		for (int x = 0; x < brokers.size(); x++) {
			if (brokers.get(x).getName().toLowerCase().equals(name.toLowerCase())) {
				return false;
			}
		}
		// Convert coin list which is a list into a string array
		String[] stringCoins = Arrays.copyOf(coins, coins.length, String[].class);
		StrategyFactory strategyFactory = new StrategyFactory();
		brokers.add(new Broker(name, stringCoins, strategyFactory.createStrategy(strategy)));
		return true;
	}
	
	/**
	 * Removes a Broker from the ArrayList
	 * @param index The index of the Broker that the user wants removed
	 */
	public void removeBroker(int index) {
		brokers.remove(index);
	}
	
	/**
	 * Removes a Broker from the ArrayList
	 * @param name The to be removed Broker's name
	 */
	public void removeBroker(String name) {
		for (int x = 0; x < brokers.size(); x++) {
			if (brokers.get(x).getName().toLowerCase().equals(name)) {
				brokers.remove(x);
			}
		}
	}
	
	public ArrayList<Broker> getBrokers() {
		return brokers;
	}
	
	/**
	 * ToString() method used for debugging
	 */
	public String toString() {
		String tempString = "";
		for (int x = 0; x < brokers.size(); x++) {
			tempString += brokers.get(x).getName() + Arrays.toString(brokers.get(x).getCoins()) + brokers.get(x).getStrategy() + "\n";
		}
		return tempString;
	}
}
