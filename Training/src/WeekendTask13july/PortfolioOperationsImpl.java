package WeekendTask13july;

import java.util.HashMap;
import java.util.Map;

public class PortfolioOperationsImpl implements PortfolioOperations {
    Map<String, Integer> holdings = new HashMap<>();

    public void buyStock(String symbol, int quantity) {
        holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
        System.out.println("Bought " + quantity + " shares of " + symbol);
    }

    public void sellStock(String symbol, int quantity) {
        if (!holdings.containsKey(symbol) || holdings.get(symbol) < quantity) {
            System.out.println("Not enough shares to sell.");
        } else {
            holdings.put(symbol, holdings.get(symbol) - quantity);
            System.out.println("Sold " + quantity + " shares of " + symbol);
            if (holdings.get(symbol) == 0) {
                holdings.remove(symbol);
            }
        }
    }

    public void ViewHoldingStatement(Map<String, Stock> stockMarket) {
        System.out.println("\nYour Holdings:");
        for (String symbol : holdings.keySet()) {
            Stock stock = stockMarket.get(symbol);
            int quantity = holdings.get(symbol);
            System.out.printf("%s (%s): %d shares worth ₹%.2f\n", stock.name, symbol, quantity, quantity * stock.price);
        }
    }
}
