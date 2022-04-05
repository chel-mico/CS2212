package cryptoTrader.utils;

public class Broker {
	private String[] coins;
	private String strategy;
	
	public Broker(String[] coins, String strategy) {
		this.coins = coins;
		this.strategy = strategy;
	}
}
