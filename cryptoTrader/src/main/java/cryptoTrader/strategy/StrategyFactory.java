package cryptoTrader.strategy;

/**
 * Abstract factory class for factory implementation of strategies
 *
 */

public class StrategyFactory {
    public StrategyFactory() {
    	
    }
    
    public Strategy createStrategy(String name) {
    	switch(name) {
    	case "Strategy-A" :
    		return new StrategyA();
    	case "Strategy-B" :
    		return new StrategyA();
    	case "Strategy-C" :
    		return new StrategyA();
    	case "Strategy-D" :
    		return new StrategyA();
    	default :
    		return null;
    	}
    }
}