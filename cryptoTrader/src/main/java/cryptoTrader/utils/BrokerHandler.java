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
			if (brokers.get(x).getName().toLowerCase().equals(name)) {
				return false;
			}
		}
		brokers.add(new Broker(name.toLowerCase(), coins, strategy));
		return true;
	}
	
	public void removeBroker(int index) {
		brokers.remove(index);
	}
	
	public void removeBroker(String name) {
		for (int x = 0; x < brokers.size(); x++) {
			if (brokers.get(x).getName().toLowerCase().equals(name)) {
				brokers.remove(x);
			}
		}
	}
	public String toString() {
		String tempString = "";
		for (int x = 0; x < brokers.size(); x++) {
			tempString += brokers.get(x).getName() + "\n";
		}
		return tempString;
	}
}
