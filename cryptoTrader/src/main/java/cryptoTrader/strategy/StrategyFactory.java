package cryptoTrader.strategy;

/**
 * Factory class for factory implementation of strategies
 *
 */

public class StrategyFactory {
	/**
	 * Constructor for StrategyFactory
	 */
    public StrategyFactory() {
    	
    }

	/**
	 * Creating strategy objects from within the factory method
	 * @param name Name of Strategy
	 * @return Strategy() Specified strategy
	 */
	public Strategy createStrategy(String name) {
    	switch(name) {
    	case "Strategy-A" :
    		return new StrategyA();
    	case "Strategy-B" :
    		return new StrategyB();
    	case "Strategy-C" :
    		return new StrategyC();
    	case "Strategy-D" :
    		return new StrategyD();
    	default :
    		return null;
    	}
    }
}