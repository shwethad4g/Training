package com.example.Ecommerce_Mini_Project.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponseDTO {
    private Long orderId;
    private String address;
    private LocalDateTime orderDate;
    private List<CartItemDTO> items;
}
