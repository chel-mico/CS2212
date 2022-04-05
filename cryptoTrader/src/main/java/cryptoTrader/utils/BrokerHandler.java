package cryptoTrader.utils;

import java.util.ArrayList;

public class BrokerHandler {

	private ArrayList<Broker> brokers;
	
	public BrokerHandler() {
		brokers = new ArrayList<Broker>();
	}
	
	public boolean addBroker(String name, String[] coins, String strategy) {
		// Check if broker already exists
		for (int x = 0; x < brokers.size(); x++) {
			if (brokers.get(x).getName().equals(name)) {
				return false;
			}
		}
		brokers.add(new Broker(name, coins, strategy));
		return true;
	}
	
	public String toString() {
		String tempString = "";
		for (int x = 0; x < brokers.size(); x++) {
			tempString += brokers.get(x).getName();
		}
		return tempString;
	}
}
