package com.example.Ecommerce_Mini_Project.controller;


import com.example.Ecommerce_Mini_Project.data_factory.OrderTestDataFactory;
import com.example.Ecommerce_Mini_Project.dto.OrderRequestDTO;
import com.example.Ecommerce_Mini_Project.dto.OrderResponseDTO;
import com.example.Ecommerce_Mini_Project.model.Order;
import com.example.Ecommerce_Mini_Project.model.OrderItem;
import com.example.Ecommerce_Mini_Project.repository.OrderItemRepository;
import com.example.Ecommerce_Mini_Project.service.OrderPdfService;
import com.example.Ecommerce_Mini_Project.service.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @Mock
    private OrderItemRepository orderRepository;

    @Mock
    private OrderPdfService orderPDFService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void placeOrder_withValidRequest_returnsOrderResponse() {
        OrderRequestDTO requestDTO = OrderTestDataFactory.createOrderRequestDTO();
        OrderResponseDTO responseDTO = OrderTestDataFactory.createOrderResponseDTO();

        when(orderService.placeOrder(requestDTO)).thenReturn(responseDTO);

        OrderResponseDTO result = orderController.placeOrder(requestDTO);

        assertNotNull(result);
        assertEquals(responseDTO, result);
        verify(orderService, times(1)).placeOrder(requestDTO);
    }

    @Test
    void getOrderHistory_withValidUserId_returnsOrderList() {
        Long userId = 1L;
        List<OrderResponseDTO> responseList = List.of(OrderTestDataFactory.createOrderResponseDTO());

        when(orderService.getOrderHistory(userId)).thenReturn(responseList);

        List<OrderResponseDTO> result = orderController.getOrderHistory(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(orderService, times(1)).getOrderHistory(userId);
    }

    @Test
    void downloadOrderInvoice_withExistingOrder_exportsPDF() throws IOException {
        Long orderId = 1L;
        Order order = OrderTestDataFactory.createOrder();
        OrderItem orderItem = OrderTestDataFactory.createOrderItemWithOrder(order);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(orderItem));

        HttpServletResponse response = mock(HttpServletResponse.class);

        orderController.downloadOrderInvoice(orderId, response);

        verify(orderRepository, times(1)).findById(orderId);
        verify(response, times(1)).setContentType("application/pdf");
        verify(response, times(1)).setHeader(eq("Cache-Control"), anyString());
        verify(orderPDFService, times(1)).exportOrderToPDF(response, order);
    }

    @Test
    void downloadOrderInvoice_withNonExistingOrder_throwsException() {
        Long orderId = 99L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        HttpServletResponse response = mock(HttpServletResponse.class);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> orderController.downloadOrderInvoice(orderId, response));

        assertTrue(ex.getMessage().contains("Order not found with ID: " + orderId));
        verify(orderRepository, times(1)).findById(orderId);
        verifyNoInteractions(orderPDFService);
    }
}
