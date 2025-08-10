package com.example.Ecommerce_Mini_Project.data_factory;



import com.example.Ecommerce_Mini_Project.dto.OrderRequestDTO;
import com.example.Ecommerce_Mini_Project.model.*;
import java.time.LocalDateTime;
import java.util.List;

public class OrderServiceTestDataFactory {

    public static OrderRequestDTO createOrderRequestDTO(Long userId, String address) {
        OrderRequestDTO dto = new OrderRequestDTO();
        dto.setUserId(userId);
        dto.setAddress(address);
        return dto;
    }

    public static User createUser(Long id) {
        User user = new User();
        user.setId(id);
        user.setUsername("testuser");
        return user;
    }

    public static Product createProduct(Long id, String name, int qty, double price) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setQuantity(qty);
        product.setPrice(price);
        return product;
    }

    public static CartItem createCartItem(User user, Product product, int quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        return cartItem;
    }

    public static Order createOrder(User user, String address, List<OrderItem> items) {
        Order order = new Order();
        order.setId(1L);
        order.setUser(user);
        order.setAddress(address);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderItems(items);
        return order;
    }

    public static OrderItem createOrderItem(Long id, Product product, int qty, double price) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(id);
        orderItem.setProduct(product);
        orderItem.setQuantity(qty);
        orderItem.setPrice(price);
        return orderItem;
    }

}
