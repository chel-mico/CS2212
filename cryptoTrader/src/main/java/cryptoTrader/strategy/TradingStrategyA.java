package strategy;
import java.util.*;
// import utils.DataFetcher;

/**
 * Trading Strategy A:
 * If BTC > $50,000 
 */
public class TradingStrategyA extends Strategy {

    public TradingStrategyA (String strategy, String name, String[] coin, List<Double> price) {
        super(strategy, name, coin, price);


    }

}