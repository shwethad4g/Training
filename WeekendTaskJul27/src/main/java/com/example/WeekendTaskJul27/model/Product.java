package com.example.WeekendTaskJul27.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long productId;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private double price;

    @Column(name="stock_quantity")
    private int stockQuantity;

    @Column(name="category")
    private String category;

    @Column(name="size")
    private String size;
}
