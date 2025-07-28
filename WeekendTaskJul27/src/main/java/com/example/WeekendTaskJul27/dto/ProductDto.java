package com.example.WeekendTaskJul27.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long productId;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private String category;
    private String size;
}
