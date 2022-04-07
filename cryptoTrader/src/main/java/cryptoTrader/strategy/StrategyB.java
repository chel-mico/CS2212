package cryptoTrader.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class StrategyB extends Strategy {
	public StrategyB() {
		super("Strategy-B", new String[3], "bitcoin");
		this.coins[0] = "bitcoin";
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String, Integer> executeStrategy(Map<String, Double> prices) {
		Map<String, Integer> trade = new HashMap<String, Integer>();
		try {
			if (prices.get("bitcoin") < 60000.0) {
				trade.put("Buy", 1);
			} else {
				trade.put("Sell", 1);
			}
		} catch (Exception e) {
			trade.put("Fail", 0);
		}
		return trade;
	}
}