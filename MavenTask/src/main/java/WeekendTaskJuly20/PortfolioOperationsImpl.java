package WeekendTaskJuly20;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class PortfolioOperationsImpl implements PortfolioOperations {
    private Map<String, Map<String, Integer>> customerHoldings = new HashMap<>();
    private Customer customer;

    public void setCustomer(Customer customer) {
        this.customer = customer;
        customerHoldings.putIfAbsent(customer.getUsername(), new HashMap<>());
    }

    public void buyStock(String symbol, int qty) {
        Map<String, Integer> holdings = customerHoldings.get(customer.getUsername());
        holdings.put(symbol, holdings.getOrDefault(symbol, 0) + qty);
        System.out.println("Bought " + qty + " shares of " + symbol);
    }

    public void sellStock(String symbol, int qty) {
        Map<String, Integer> holdings = customerHoldings.get(customer.getUsername());
        int current = holdings.getOrDefault(symbol, 0);

        if (current >= qty) {
            holdings.put(symbol, current - qty);
            System.out.println("Sold " + qty + " shares of " + symbol);
        }
        else {
            System.out.println("Not enough shares to sell.");
        }
    }

    public void ViewHoldingStatement(Map<String, Stock> stockMarket) {
        Map<String, Integer> holdings = customerHoldings.get(customer.getUsername());
        System.out.println("\n--- Holding Statement for " + customer.getUsername() + " ---");
        double total = 0;

        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            String symbol = entry.getKey();
            int qty = entry.getValue();
            Stock stock = stockMarket.get(symbol);
            double value = qty * stock.getPrice();
            System.out.println(stock.getName() + " (" + symbol + "): " + qty +
                    " shares @ Rs" + stock.getPrice() + " = Rs" + value);
            total += value;
        }

        System.out.println("Total Portfolio Value: Rs" + total);
    }

    public Map<String, Integer> getCurrentHoldings() {
        return customerHoldings.get(customer.getUsername());
    }

    public void generatePDFReport(Map<String, Stock> stockMarket) {
        try {
            Document document = new Document();
            String fileName = customer.getUsername() + "_portfolio.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            document.add(new Paragraph("Stock Portfolio Report for "
                    + customer.getUsername()));
            document.add(new Paragraph(" "));
            PdfPTable table = new PdfPTable(4);
            table.addCell("Stock Name");
            table.addCell("Symbol");
            table.addCell("Quantity");
            table.addCell("Value (INR)");
            double total = 0;

            for (Map.Entry<String, Integer> entry : getCurrentHoldings().entrySet()) {
                String symbol = entry.getKey();
                int qty = entry.getValue();
                Stock stock = stockMarket.get(symbol);
                double value = qty * stock.getPrice();
                total += value;

                table.addCell(stock.getName());
                table.addCell(symbol);
                table.addCell(String.valueOf(qty));
                table.addCell("Rs" + value);
            }

            document.add(table);
            document.add(new Paragraph("Total Portfolio Value: Rs" + total));
            document.close();
            System.out.println("PDF Report generated: " + fileName);
        }
        catch (Exception e) {
            System.out.println("Failed to generate PDF: " + e.getMessage());
        }
    }
}
