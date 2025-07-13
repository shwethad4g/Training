package WeekendTask13july;

import java.util.Map;

public interface PortfolioOperations {
    void buyStock(String symbol, int quantity);

    void sellStock(String symbol, int quantity);

    void ViewHoldingStatement(Map<String, Stock> stockMarket);
}
