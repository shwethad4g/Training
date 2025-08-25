package com.example.Personal_Finance_Tracker_Final_Project.controller;

import com.example.Personal_Finance_Tracker_Final_Project.dto.BalanceDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import com.example.Personal_Finance_Tracker_Final_Project.service.BalanceService;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class BalanceControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private BalanceController balanceController;

    @Mock
    private BalanceService balanceService;

    @Mock
    private UserRepository userRepository;

    private AutoCloseable mocks;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String URL = "/api/balance";
    private static final String EMAIL = "zara@gmail.com";
    private static final long USER_ID = 42L;

    @BeforeEach
    void setup() {
        mocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(balanceController).build();


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
    void getCurrentBalance_success() throws Exception {

        User user = new User();
        user.setId(USER_ID);
        user.setEmail(EMAIL);
        user.setUsername("zara");
        when(userRepository.findByEmailIgnoreCase(EMAIL)).thenReturn(Optional.of(user));

        BalanceDTO dto = new BalanceDTO();

        when(balanceService.getCurrentBalance(eq(USER_ID))).thenReturn(dto);
        String expectedJson = objectMapper.writeValueAsString(dto);
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get(URL)
                        .accept(MediaType.APPLICATION_JSON)
        );
        Assertions.assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
        Assertions.assertEquals(expectedJson, result.andReturn().getResponse().getContentAsString());
    }


}
