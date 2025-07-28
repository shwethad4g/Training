package com.example.WeekendTaskJul27.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private String category;
    private String size;
}
