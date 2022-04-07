package cryptoTrader.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class StrategyD extends Strategy {
	public StrategyD() {
		super("Strategy-A", new String[3], "ehtereum");
		this.coins[0] = "ethereum";
		// TODO Auto-generated constructor stub
	}

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
