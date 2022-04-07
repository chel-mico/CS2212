package cryptoTrader.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Trading Strategy C
 * If Ethereum < $4,000 or Dogecoin < $0.4, buy 2000 dogecoin, else sell 1000 dogecoin
 */
public class StrategyC extends Strategy {
	public StrategyC() {
		super("Strategy-C", new String[3], "dogecoin");
		this.coins[0] = "ethereum";
		this.coins[1] = "dogecoin";
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String, Integer> executeStrategy(Map<String, Double> prices) {
		Map<String, Integer> trade = new HashMap<String, Integer>();
		try {
			if (prices.get("ethereum") < 4000.0 || prices.get("dogecoin") < 0.40) {
				trade.put("Buy", 2000);
			} else {
				trade.put("Sell", 1000);
			}
		} catch (Exception e) {
			trade.put("Fail", 0);
		}
		return trade;
	}
}