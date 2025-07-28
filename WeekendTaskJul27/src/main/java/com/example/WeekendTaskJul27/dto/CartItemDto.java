package com.example.WeekendTaskJul27.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {
    private Long cartItemId;
    private int cartId;
    private Long productId;
    private int quantity;
}
