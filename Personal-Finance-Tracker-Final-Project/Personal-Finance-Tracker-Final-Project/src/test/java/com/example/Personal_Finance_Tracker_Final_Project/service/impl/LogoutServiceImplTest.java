package com.example.Personal_Finance_Tracker_Final_Project.service.impl;

import com.example.Personal_Finance_Tracker_Final_Project.dao.LogoutDAO;
import com.example.Personal_Finance_Tracker_Final_Project.data_factory.LogoutServiceImplTestDataFactory;
import com.example.Personal_Finance_Tracker_Final_Project.dto.LogoutResponseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.LogoutResponseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LogoutServiceImplTest {

    @Mock
    private LogoutDAO logoutDAO;

    @Mock
    private LogoutResponseMapper logoutResponseMapper;

    @InjectMocks
    private LogoutServiceImpl logoutService;

    @Test
    void testLogout_whenCalled_thenReturnLogoutResponseDTO() {
        Long userId = LogoutServiceImplTestDataFactory.userId;
        String token = LogoutServiceImplTestDataFactory.token;
        LogoutResponseDTO responseDTO = LogoutServiceImplTestDataFactory.logoutResponseDTO;

        when(logoutResponseMapper.toDTO("Successfully logged out")).thenReturn(responseDTO);

        LogoutResponseDTO result = logoutService.logout(userId, token);

        assertThat(result).isEqualTo(responseDTO);
    }
}
