package com.example.WeekendTaskJul27.service.impl;

import com.example.WeekendTaskJul27.model.Cart;
import com.example.WeekendTaskJul27.model.CartItem;
import com.example.WeekendTaskJul27.repository.CartItemRepository;
import com.example.WeekendTaskJul27.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> getItemsByCart(Cart cart) {
        return cartItemRepository.findByCart(cart);
    }
}
