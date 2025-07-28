package com.example.WeekendTaskJul27.service;

import com.example.WeekendTaskJul27.model.CartItem;
import com.example.WeekendTaskJul27.model.Cart;

import java.util.List;

public interface CartItemService {
    List<CartItem> getItemsByCart(Cart cart);
}
