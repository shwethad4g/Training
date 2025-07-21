package com.example.StockPortfolioSpringProject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    private String symbol;
    private String name;
    private double price;
}
