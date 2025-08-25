package com.example.Personal_Finance_Tracker_Final_Project.service.impl;

import com.example.Personal_Finance_Tracker_Final_Project.dao.BalanceDAO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.BalanceDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.BalanceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class BalanceServiceImplTest {

    @Mock
    private BalanceDAO balanceDAO;

    @Mock
    private BalanceMapper balanceMapper;

    @InjectMocks
    private BalanceServiceImpl balanceService;

    private static class TestDataFactory {
        static Long userId() {
            return 1L;
        }

        static double balance() {
            return 1000.50;
        }

        static BalanceDTO balanceDTO() {
            BalanceDTO dto = new BalanceDTO();
            dto.setBalance(balance());
            return dto;
        }
    }

    private Long userId;
    private double balance;
    private BalanceDTO balanceDTO;

    @BeforeEach
    void setUp() {
        userId = TestDataFactory.userId();
        balance = TestDataFactory.balance();
        balanceDTO = TestDataFactory.balanceDTO();
    }

    @Test
    void testGetCurrentBalance_whenUserIdIsValid_thenReturnBalanceDTO() {
        Mockito.when(balanceDAO.getCurrentBalance(userId)).thenReturn(balance);
        Mockito.when(balanceMapper.toDTO(balance)).thenReturn(balanceDTO);

        BalanceDTO result = balanceService.getCurrentBalance(userId);

        assertNotNull(result);
        assertEquals(balanceDTO.getBalance(), result.getBalance());
    }
}