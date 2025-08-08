package com.example.Ecommerce_Mini_Project.repository;


import com.example.Ecommerce_Mini_Project.model.CartItem;
import com.example.Ecommerce_Mini_Project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    List<CartItem> findByUserId(int userId);


    @Query("SELECT ci FROM CartItem ci JOIN FETCH ci.user WHERE ci.user.id = :userId")
    List<CartItem> findByUserId(@Param("userId") Long userId);
}
