package com.example.Personal_Finance_Tracker_Final_Project.controller;

import com.example.Personal_Finance_Tracker_Final_Project.dto.BudgetDTO;
import com.example.Personal_Finance_Tracker_Final_Project.exception.GlobalExceptionHandler;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import com.example.Personal_Finance_Tracker_Final_Project.service.BudgetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BudgetControllerTest {

    private MockMvc mockMvc;
    private AutoCloseable mocks;

    @InjectMocks
    private BudgetController controller;

    @Mock
    private BudgetService budgetService;

    @Mock
    private UserRepository userRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String URL = "/api/budgets";
    private static final String EMAIL = "test@example.com";
    private static final long USER_ID = 1L;

    @BeforeEach
    void setup() {
        mocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        SecurityContextHolder.clearContext();
        if (mocks != null) mocks.close();
    }

    @Test
    void createBudget_success_returns200() throws Exception {
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(EMAIL)
                .password("password")
                .authorities("ROLE_USER")
                .build();

        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        User user = new User();
        user.setId(USER_ID);
        user.setEmail(EMAIL);
        when(userRepository.findByEmailIgnoreCase(EMAIL)).thenReturn(Optional.of(user));

        BudgetDTO requestDto = new BudgetDTO();
        requestDto.setYear(2025);
        requestDto.setMonth(8);
        requestDto.setCategory("Food");
        requestDto.setAmount(15000.0);

        BudgetDTO responseDto = new BudgetDTO();
        when(budgetService.createBudget(any(BudgetDTO.class), eq(USER_ID))).thenReturn(responseDto);

        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk());
    }

    @Test
    void createBudget_userNotFound_returns404() throws Exception {
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(EMAIL)
                .password("password")
                .authorities("ROLE_USER")
                .build();

        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        when(userRepository.findByEmailIgnoreCase(EMAIL)).thenReturn(Optional.empty());

        BudgetDTO requestDto = new BudgetDTO();
        requestDto.setYear(2025);
        requestDto.setMonth(8);
        requestDto.setCategory("Food");
        requestDto.setAmount(15000.0);

        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }


}