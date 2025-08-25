package com.example.Personal_Finance_Tracker_Final_Project.controller;

import com.example.Personal_Finance_Tracker_Final_Project.dto.CategoryReportDTO;
import com.example.Personal_Finance_Tracker_Final_Project.service.CategoryReportService;
import com.example.Personal_Finance_Tracker_Final_Project.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class CategoryReportControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CategoryReportController controller;

    @Mock
    private CategoryReportService reportService;

    @Mock
    private JwtUtil jwtUtil;

    private AutoCloseable mocks;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String URL = "/api/reports/category";
    private static final String EMAIL = "zara@gmail.com";
    private static final String TOKEN = "valid.jwt.token";
    private static final long USER_ID = 42L;

    @BeforeEach
    void setup() {
        mocks = MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();


        UserDetails principal = org.springframework.security.core.userdetails.User
                .withUsername(EMAIL)
                .password("x")
                .authorities("ROLE_USER")
                .build();
        Authentication auth = new UsernamePasswordAuthenticationToken(
                principal, null, principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @AfterEach
    void tearDown() throws Exception {
        SecurityContextHolder.clearContext();
        if (mocks != null) mocks.close();
    }

    @Test
    void getCategoryReport_success_withDates_returns200AndBody() throws Exception {

        LocalDate start = LocalDate.of(2025, 8, 1);
        LocalDate end   = LocalDate.of(2025, 8, 21);

        when(jwtUtil.extractUserId(TOKEN)).thenReturn(USER_ID);

        CategoryReportDTO dto = new CategoryReportDTO();
        Map<String, Double> byCat = new LinkedHashMap<>();
        byCat.put("Food", 1200.0);
        byCat.put("Travel", 800.0);
        dto.setExpensesByCategory(byCat);
        dto.setTotalExpenses(2000.0);

        when(reportService.getCategoryReport(eq(USER_ID), eq(start), eq(end))).thenReturn(dto);

        String expectedJson = objectMapper.writeValueAsString(dto);


        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get(URL)
                        .param("startDate", "2025-08-01")
                        .param("endDate", "2025-08-21")
                        .header("Authorization", "Bearer " + TOKEN)
                        .accept(MediaType.APPLICATION_JSON)
        );


        Assertions.assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
        Assertions.assertEquals(expectedJson, result.andReturn().getResponse().getContentAsString());
    }

    @Test
    void getCategoryReport_missingAuthorizationHeader_returns400() throws Exception {


        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get(URL)
                        .param("startDate", "2025-08-01")
                        .param("endDate", "2025-08-21")
                        .accept(MediaType.APPLICATION_JSON)
        );

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), result.andReturn().getResponse().getStatus());
    }
}
