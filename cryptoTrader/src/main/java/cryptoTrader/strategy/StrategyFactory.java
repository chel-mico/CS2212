package cryptoTrader.strategy;
import java.util.*.

/**
 * Abstract factory class for factory implementation of strategies
 *
 */

public abstract class StrategyFactory {
    /**
     * Constructor
     * @param coin
     * @param name
     * @param strategy
     * @param price
     */
    public abstract Strategy executeStrategy(String strategy, String name, String[] coin, List<Double> price);
}