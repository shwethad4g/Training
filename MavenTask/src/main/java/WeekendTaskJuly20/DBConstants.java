package WeekendTaskJuly20;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DBConstants {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/customers";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "root";
    public static Map<String, Stock> stockMarket = new HashMap<>();
    public static PortfolioOperations portfolio = new PortfolioOperationsImpl();
    public static Scanner scanner = new Scanner(System.in);
    public static Customer currentCustomer = null;

}
