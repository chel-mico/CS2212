package cryptoTrader.utils;

public class Broker {
	private String name;
	private String[] coins;
	private String strategy;
	
	public Broker(String name, String[] coins, String strategy) {
		this.name = name;
		this.coins = coins;
		this.strategy = strategy;
	}
	
	public String getName() {
		return name;
	}
}
