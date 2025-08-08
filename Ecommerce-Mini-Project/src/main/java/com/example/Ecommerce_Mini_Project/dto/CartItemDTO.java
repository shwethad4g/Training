package com.example.Ecommerce_Mini_Project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {
    private Long id;
    private Long userId;
    private Long productId;
    private String productName;
    private double productPrice;
    private int quantity;
    private double totalPrice;
}
