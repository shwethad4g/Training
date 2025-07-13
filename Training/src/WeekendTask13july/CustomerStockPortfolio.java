package WeekendTask13july;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CustomerStockPortfolio {
    static Map<String, Stock> stockMarket = new HashMap<>();
    static PortfolioOperationsImpl portfolio = new PortfolioOperationsImpl();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeStocks();

        while (true) {
            System.out.println("\n--- Stock Portfolio Menu ---");
            System.out.println("1. View Stocks List");
            System.out.println("2. Buy Stocks");
            System.out.println("3. Sell Stocks");
            System.out.println("4. Show Holding Statement");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewStocks();
                    break;
                case 2:
                    buyStocks();
                    break;
                case 3:
                    sellStocks();
                    break;
                case 4:
                    portfolio.ViewHoldingStatement(stockMarket);
                    break;
                case 5:
                    System.out.println("Thanks for using StockPortfolioApp!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }

    static void initializeStocks() {
        stockMarket.put("INFY", new Stock("INFY", "Infosys", 1500.00));
        stockMarket.put("TCS", new Stock("TCS", "Tata Consultancy Services", 3500.00));
        stockMarket.put("RELIANCE", new Stock("RELIANCE", "Reliance Industries", 2800.00));
        stockMarket.put("HDFC", new Stock("HDFC", "HDFC Bank", 1700.00));
    }

    static void viewStocks() {
        System.out.println("\nAvailable Stocks:");
        for (Stock stock : stockMarket.values()) {
            System.out.println(stock.name + " (" + stock.symbol + "): ₹" + String.format("%.2f", stock.price));

        }
    }

    static void buyStocks() {
        System.out.print("Enter stock symbol to buy: ");
        String symbol = scanner.next().toUpperCase();
        if (!stockMarket.containsKey(symbol)) {
            System.out.println("Stock not found.");
            return;
        }
        System.out.print("Enter quantity to buy: ");
        int qty = scanner.nextInt();
        portfolio.buyStock(symbol, qty);
    }

    static void sellStocks() {
        System.out.print("Enter stock symbol to sell: ");
        String symbol = scanner.next().toUpperCase();
        if (!stockMarket.containsKey(symbol)) {
            System.out.println("Stock not found.");
            return;
        }
        System.out.print("Enter quantity to sell: ");
        int qty = scanner.nextInt();
        portfolio.sellStock(symbol, qty);
    }
}



