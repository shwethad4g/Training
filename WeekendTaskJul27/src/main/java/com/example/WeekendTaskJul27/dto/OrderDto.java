package com.example.WeekendTaskJul27.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long orderId;
    private int customerId;
    private Timestamp orderDate;
    private double totalAmount;
    private List<OrderItemDto> items;
}
