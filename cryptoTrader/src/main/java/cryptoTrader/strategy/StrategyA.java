package cryptoTrader.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Trading Strategy A
 * If Bitcoin > $50,000 and Cardana < $100, buy 200 Cardano
 * If not, buy 50 Cardano
 */
public class StrategyA extends Strategy {

	public StrategyA() {
		super("Strategy-A", new String[3], "cardano");
		this.coins[0] = "bitcoin";
		this.coins[1] = "cardano";
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String, Integer> executeStrategy(Map<String, Double> prices) {
		Map<String, Integer> trade = new HashMap<String, Integer>();
		try {
			if (prices.get("bitcoin") > 50000.0 && prices.get("cardano") < 100.0) {
				trade.put("Buy", 200);
			} else {
				trade.put("Buy", 50);
			}
		} catch (Exception e) {
			trade.put("Fail", 0);
		}
		return trade;
	}

}