package cryptoTrader.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Strategy D
 * If Ethereum < $6,000 buy 5 Ethereum, else sell 5 ethereum
 */
public class StrategyD extends Strategy {
	public StrategyD() {
		super("Strategy-D", new String[3], "ethereum");
		this.coins[0] = "ethereum";
		// TODO Auto-generated constructor stub
	}

	/**
	 * Executes strategy D
	 * @param prices prices of ethereum
	 */
	@Override
	public Map<String, Integer> executeStrategy(Map<String, Double> prices) {
		Map<String, Integer> trade = new HashMap<String, Integer>();
		try {
			if (prices.get("ethereum") < 6000.0) {
				trade.put("Buy", 5);
			} else {
				trade.put("Sell", 5);
			}
		} catch (Exception e) {
			trade.put("Fail", 0);
		}
		return trade;
	}
}
