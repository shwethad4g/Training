package com.example.Personal_Finance_Tracker_Final_Project.controller;

import com.example.Personal_Finance_Tracker_Final_Project.dto.MonthlyStatementDTO;
import com.example.Personal_Finance_Tracker_Final_Project.service.MonthlyStatementService;
import com.example.Personal_Finance_Tracker_Final_Project.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MonthlyStatementControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private MonthlyStatementController monthlyStatementController;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private MonthlyStatementService statementService;

    private static final String TOKEN = "jwt-token";
    private static final String AUTH_HEADER = "Bearer " + TOKEN;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(monthlyStatementController).build();
        SecurityContextHolder.getContext()
                .setAuthentication(new TestingAuthenticationToken("user@example.com", "password"));
    }

    @Test
    public void testGetMonthlyStatement_whenValidRequest_thenReturnOkResponse() throws Exception {
        MonthlyStatementDTO dto = new MonthlyStatementDTO();
        dto.setYear(2025);
        dto.setMonth(8);
        dto.setTotalExpenses(BigDecimal.valueOf(100.0));
        dto.setTotalIncomes(BigDecimal.valueOf(2000.0));

        when(jwtUtil.extractUserId(TOKEN)).thenReturn(1L);
        when(statementService.getMonthlyStatement(anyLong(), anyInt(), anyInt())).thenReturn(dto);

        ResultActions result = mockMvc.perform(get("/api/monthly")
                .param("year", "2025")
                .param("month", "8")
                .header("Authorization", AUTH_HEADER)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.year").value(2025))
                .andExpect(jsonPath("$.month").value(8))
                .andExpect(jsonPath("$.totalExpenses").value(100.0))
                .andExpect(jsonPath("$.totalIncomes").value(2000.0));
    }
}
