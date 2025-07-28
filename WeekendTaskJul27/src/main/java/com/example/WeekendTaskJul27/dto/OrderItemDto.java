package com.example.WeekendTaskJul27.dto;



import lombok.Data;

@Data
public class OrderItemDto {
    private int orderItemId;
    private int productId;
    private String productName;
    private double price;
    private int quantity;
    private double total;

    public OrderItemDto(int orderItemId, int productId, String productName, double price, int quantity) {
        this.orderItemId = orderItemId;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.total = price * quantity;
    }
}
