package com.example.WeekendTaskJul27.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private int cartId;
    private int customerId;
    private Timestamp createdAt;
    private List<CartItemDto> items;
}
