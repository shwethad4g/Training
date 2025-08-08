package com.example.Ecommerce_Mini_Project.service;


import com.example.Ecommerce_Mini_Project.model.Order;
import com.example.Ecommerce_Mini_Project.model.OrderItem;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;

@Service
public class OrderPdfService {

    public void exportOrderToPDF(HttpServletResponse response, Order order) throws IOException {
        try {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();
            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontTitle.setSize(18);
            fontTitle.setColor(Color.BLUE);

            Paragraph title = new Paragraph("Order Invoice", fontTitle);
            title.setAlignment(Paragraph.ALIGN_CENTER);

            document.add(title);
            document.add(new Paragraph("Order ID: " + order.getId()));
            document.add(new Paragraph("Order Date: " + order.getOrderDate()));
            document.add(new Paragraph("Delivery Address: " + order.getAddress()));
            document.add(new Paragraph("Customer ID: " + order.getUser().getId()));
            document.add(new Paragraph(" ")); // spacer

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100f);
            table.setSpacingBefore(10);

            table.addCell("Product");
            table.addCell("Quantity");
            table.addCell("Price");
            table.addCell("Total");

            double grandTotal = 0;
            for (OrderItem item : order.getOrderItems()) {
                table.addCell(item.getProduct().getName());
                table.addCell(String.valueOf(item.getQuantity()));
                table.addCell(String.valueOf(item.getPrice()));
                double total = item.getQuantity() * item.getPrice();
                table.addCell(String.valueOf(total));
                grandTotal += total;
            }

            document.add(table);

            Paragraph totalParagraph = new Paragraph("Grand Total: ₹" + grandTotal);
            totalParagraph.setAlignment(Paragraph.ALIGN_RIGHT);
            totalParagraph.setSpacingBefore(10);
            document.add(totalParagraph);

            document.close();
        } catch (Exception e) {
            throw new IOException("Error generating PDF", e);
        }
    }
}
