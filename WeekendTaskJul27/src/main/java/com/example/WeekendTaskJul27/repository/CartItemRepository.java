package com.example.WeekendTaskJul27.repository;

import com.example.WeekendTaskJul27.model.Cart;
import com.example.WeekendTaskJul27.model.CartItem;
import com.example.WeekendTaskJul27.model.Customer;
import com.example.WeekendTaskJul27.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByCart(Cart cart);
    List<CartItem> findByCart_CartId(int cartId);
    List<CartItem> findByCustomer(Customer customer);
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

}
