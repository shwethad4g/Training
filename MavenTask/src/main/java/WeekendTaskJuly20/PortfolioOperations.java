package WeekendTaskJuly20;

import java.util.Map;

public interface PortfolioOperations {
    void buyStock(String symbol, int quantity);

    void sellStock(String symbol, int quantity);

    void ViewHoldingStatement(Map<String, Stock> stockMarket);

    void setCustomer(Customer customer);

    void generatePDFReport(Map<String, Stock> stockMarket);
}