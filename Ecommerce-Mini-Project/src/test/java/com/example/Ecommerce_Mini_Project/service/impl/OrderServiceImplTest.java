package com.example.Ecommerce_Mini_Project.service.impl;


import com.example.Ecommerce_Mini_Project.data_factory.OrderServiceTestDataFactory;
import com.example.Ecommerce_Mini_Project.dto.CartItemDTO;
import com.example.Ecommerce_Mini_Project.dto.OrderRequestDTO;
import com.example.Ecommerce_Mini_Project.dto.OrderResponseDTO;
import com.example.Ecommerce_Mini_Project.mapper.CartItemMapper;
import com.example.Ecommerce_Mini_Project.model.*;
import com.example.Ecommerce_Mini_Project.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CartItemMapper cartItemMapper;

    private User testUser;
    private Product testProduct;
    private CartItem testCartItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = OrderServiceTestDataFactory.createUser(1L);
        testProduct = OrderServiceTestDataFactory.createProduct(101L, "Test Product", 10, 100.0);
        testCartItem = OrderServiceTestDataFactory.createCartItem(testUser, testProduct, 2);
    }

    @Test
    void placeOrder_withValidData_returnsOrderResponse() {
        OrderRequestDTO request = OrderServiceTestDataFactory.createOrderRequestDTO(1L, "Test Address");

        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(cartItemRepository.findByUser(testUser)).thenReturn(List.of(testCartItem));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(cartItemMapper.toDto(testCartItem)).thenReturn(new CartItemDTO());
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order o = invocation.getArgument(0);
            o.setId(1L);
            return o;
        });

        OrderResponseDTO response = orderService.placeOrder(request);

        assertNotNull(response);
        assertEquals(1L, response.getOrderId());
        assertEquals("Test Address", response.getAddress());
        verify(userRepository).findById(1L);
        verify(productRepository).save(any(Product.class));
        verify(cartItemRepository).deleteAll(anyList());
    }

    @Test
    void placeOrder_withUserNotFound_throwsException() {
        OrderRequestDTO request = OrderServiceTestDataFactory.createOrderRequestDTO(99L, "Address");
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> orderService.placeOrder(request));
        assertEquals("User not found", ex.getMessage());
        verifyNoInteractions(cartItemRepository);
    }

    @Test
    void placeOrder_withEmptyCart_throwsException() {
        OrderRequestDTO request = OrderServiceTestDataFactory.createOrderRequestDTO(1L, "Address");
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(cartItemRepository.findByUser(testUser)).thenReturn(List.of());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> orderService.placeOrder(request));
        assertEquals("Cart is empty", ex.getMessage());
        verify(cartItemRepository).findByUser(testUser);
    }

    @Test
    void placeOrder_withOutOfStockProduct_throwsException() {
        testProduct.setQuantity(1); // less than cart quantity
        OrderRequestDTO request = OrderServiceTestDataFactory.createOrderRequestDTO(1L, "Address");

        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(cartItemRepository.findByUser(testUser)).thenReturn(List.of(testCartItem));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> orderService.placeOrder(request));
        assertTrue(ex.getMessage().contains("is out of stock"));
        verifyNoInteractions(orderRepository);
    }

    @Test
    void getOrderHistory_withValidUser_returnsOrderList() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        Product product = OrderServiceTestDataFactory.createProduct(101L, "P1", 5, 200.0);
        OrderItem orderItem = OrderServiceTestDataFactory.createOrderItem(1L, product, 1, 200.0);
        Order order = OrderServiceTestDataFactory.createOrder(testUser, "Addr", List.of(orderItem));

        when(orderRepository.findByUser(testUser)).thenReturn(List.of(order));

        List<OrderResponseDTO> history = orderService.getOrderHistory(1L);

        assertEquals(1, history.size());
        assertEquals("Addr", history.get(0).getAddress());
        assertEquals(1, history.get(0).getItems().size());
        verify(orderRepository).findByUser(testUser);
    }

    @Test
    void getOrderHistory_withUserNotFound_throwsException() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> orderService.getOrderHistory(2L));
        assertEquals("User not found", ex.getMessage());
        verifyNoInteractions(orderRepository);
    }
}
