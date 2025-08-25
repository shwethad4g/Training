package com.example.Personal_Finance_Tracker_Final_Project.controller;

import com.example.Personal_Finance_Tracker_Final_Project.dto.BudgetComparisonDTO;
import com.example.Personal_Finance_Tracker_Final_Project.exception.GlobalExceptionHandler;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import com.example.Personal_Finance_Tracker_Final_Project.service.BudgetComparisonService;
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

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class BudgetComparisonControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private BudgetComparisonController controller;

    @Mock
    private BudgetComparisonService comparisonService;

    @Mock
    private UserRepository userRepository;

    private AutoCloseable mocks;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String URL = "/api/budgets/compare";
    private static final String EMAIL = "zara@gmail.com";
    private static final long USER_ID = 42L;
    private static final int YEAR = 2025;
    private static final int MONTH = 8;

    @BeforeEach
    void setup() {
        mocks = MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
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
    void compareBudget_success_returns200WithBody() throws Exception {

        User user = new User();
        user.setId(USER_ID);
        user.setEmail(EMAIL);
        user.setUsername("zara");
        when(userRepository.findByEmailIgnoreCase(EMAIL)).thenReturn(Optional.of(user));

        BudgetComparisonDTO dto = new BudgetComparisonDTO();
        dto.setYear(YEAR);
        dto.setMonth(MONTH);

        BudgetComparisonDTO.CategoryComparison food = new BudgetComparisonDTO.CategoryComparison();
        food.setBudgetedAmount(BigDecimal.valueOf(1000.0));
        food.setActualAmount(BigDecimal.valueOf(750.0));
        food.setDifference(BigDecimal.valueOf(250.0));

        BudgetComparisonDTO.CategoryComparison travel = new BudgetComparisonDTO.CategoryComparison();
        travel.setBudgetedAmount(BigDecimal.valueOf(500.0));
        travel.setActualAmount(BigDecimal.valueOf(600.0));
        travel.setDifference(BigDecimal.valueOf(-100.0));

        Map<String, BudgetComparisonDTO.CategoryComparison> map = new LinkedHashMap<>();
        map.put("Food", food);
        map.put("Travel", travel);
        dto.setComparisons(map);

        when(comparisonService.compareBudget(eq(USER_ID), eq(YEAR), eq(MONTH))).thenReturn(dto);

        String expectedJson = objectMapper.writeValueAsString(dto);

        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get(URL)
                        .param("year", String.valueOf(YEAR))
                        .param("month", String.valueOf(MONTH))
                        .accept(MediaType.APPLICATION_JSON)
        );


        Assertions.assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
        Assertions.assertEquals(expectedJson, result.andReturn().getResponse().getContentAsString());
    }

    @Test
    void compareBudget_userNotFound_returns404WithMessage() throws Exception {

        when(userRepository.findByEmailIgnoreCase(EMAIL)).thenReturn(Optional.empty());


        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get(URL)
                        .param("year", String.valueOf(YEAR))
                        .param("month", String.valueOf(MONTH))
                        .accept(MediaType.APPLICATION_JSON)
        );


        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), result.andReturn().getResponse().getStatus());
        Assertions.assertEquals(
                "{\"message\":\"User not found for email: " + EMAIL + "\"}",
                result.andReturn().getResponse().getContentAsString()
        );
    }
}
