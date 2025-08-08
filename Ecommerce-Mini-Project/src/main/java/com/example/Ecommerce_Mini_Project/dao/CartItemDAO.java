package com.example.Ecommerce_Mini_Project.dao;



import com.example.Ecommerce_Mini_Project.model.CartItem;
import com.example.Ecommerce_Mini_Project.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartItemDAO  {

    @Autowired
    private CartItemRepository cartItemRepository;


    public void save(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }


    public List<CartItem> findByUserId(int userId) {
        return cartItemRepository.findByUserId(userId);
    }
}
