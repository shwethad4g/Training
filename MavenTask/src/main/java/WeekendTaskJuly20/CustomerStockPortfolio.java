package WeekendTaskJuly20;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CustomerStockPortfolio {
    static Map<String, Stock> stockMarket = new HashMap<>();
    static PortfolioOperations portfolio = new PortfolioOperationsImpl();
    static Scanner scanner = new Scanner(System.in);
    static Customer currentCustomer = null;
    static final String DB_URL = "jdbc:mysql://localhost:3306/customers";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "root";

    public static void initializeStocks() {
        stockMarket.put("INFY", new Stock("INFY", "Infosys", 1500.00));
        stockMarket.put("TCS", new Stock("TCS", "Tata Consultancy Services", 3500.00));
        stockMarket.put("RELIANCE", new Stock("RELIANCE", "Reliance Industries", 2800.00));
        stockMarket.put("HDFC", new Stock("HDFC", "HDFC Bank", 1700.00));
    }

    public static void viewStocks() {
        System.out.println("\nAvailable Stocks:");

        for (Stock stock : stockMarket.values()) {
            System.out.println(stock.getName() + " (" + stock.getSymbol() + "): Rs " +
                    String.format("%.2f", stock.getPrice()));
        }
    }

    public static void buyStocks() {
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

    public static void sellStocks() {
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

    public static void signUpOrLogin() {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.print("Enter your username: ");
            String username = scanner.next();
            String query = "SELECT password FROM customers WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int attempts = 3;
                while (attempts-- > 0) {
                    System.out.print("Enter your password: ");
                    String password = scanner.next();

                    if (rs.getString("password").equals(password)) {
                        currentCustomer = new Customer(username);
                        portfolio.setCustomer(currentCustomer);
                        System.out.println("Login successful. Welcome back, " + username + "!");
                        return;
                    }

                    else {
                        System.out.println("Incorrect password. Attempts left: " + attempts);
                    }
                }
                System.out.println("Too many failed attempts. Exiting.");
                System.exit(0);
            }

            else {
                System.out.print("Enter a password to create your account: ");
                String password = scanner.next();
                String insertQuery = "INSERT INTO customers (username, password) VALUES (?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                insertStmt.setString(1, username);
                insertStmt.setString(2, password);
                insertStmt.executeUpdate();
                currentCustomer = new Customer(username);
                portfolio.setCustomer(currentCustomer);
                System.out.println("Account created successfully! Welcome, " + username + ".");
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        initializeStocks();
        signUpOrLogin();

        while (true) {
            System.out.println("\n--- Stock Portfolio Menu ---");
            System.out.println("1. View Stocks List");
            System.out.println("2. Buy Stocks");
            System.out.println("3. Sell Stocks");
            System.out.println("4. Show Holding Statement");
            System.out.println("5. Generate PDF Statement");
            System.out.println("6. Exit");
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
                    portfolio.generatePDFReport(stockMarket);
                    break;
                case 6:
                    System.out.println("Thanks for using StockPortfolioApp!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }
}
