package com.example.Ecommerce_Mini_Project.data_factory;



import com.example.Ecommerce_Mini_Project.dto.OrderRequestDTO;
import com.example.Ecommerce_Mini_Project.dto.OrderResponseDTO;
import com.example.Ecommerce_Mini_Project.model.Order;
import com.example.Ecommerce_Mini_Project.model.OrderItem;

public class OrderTestDataFactory {

    public static OrderRequestDTO createOrderRequestDTO() {
        return new OrderRequestDTO();
    }

    public static OrderResponseDTO createOrderResponseDTO() {
        return new OrderResponseDTO();
    }

    public static Order createOrder() {
        return new Order();
    }

    public static OrderItem createOrderItemWithOrder(Order order) {
        OrderItem item = new OrderItem();
        item.setOrder(order);
        return item;
    }
}
