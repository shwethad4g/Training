package com.example.Ecommerce_Mini_Project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDTO {
    private Long userId;
    private String address;
}
