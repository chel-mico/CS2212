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