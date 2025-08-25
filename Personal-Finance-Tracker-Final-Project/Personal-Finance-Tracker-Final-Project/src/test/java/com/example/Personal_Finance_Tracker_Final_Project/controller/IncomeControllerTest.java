package com.example.Personal_Finance_Tracker_Final_Project.controller;

import com.example.Personal_Finance_Tracker_Final_Project.dto.IncomeDTO;
import com.example.Personal_Finance_Tracker_Final_Project.service.IncomeService;
import com.example.Personal_Finance_Tracker_Final_Project.service.impl.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IncomeControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private IncomeController incomeController;

    @Mock
    private IncomeService incomeService;

    private ObjectMapper objectMapper;

    private static final String INCOME_REQUEST_BODY =
            "{\"id\":1,\"amount\":1000.0,\"description\":null,\"date\":null,\"category\":\"Salary\"}";
    private static final String INCOME_RESPONSE_BODY =
            "{\"id\":1,\"amount\":1000.0,\"description\":null,\"date\":null,\"category\":\"Salary\"}";

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(incomeController)
                .setCustomArgumentResolvers(new AuthenticationPrincipalArgumentResolver())
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateIncome_success() throws Exception {
        // Arrange
        IncomeDTO incomeDTO = new IncomeDTO();
        incomeDTO.setId(1L);
        incomeDTO.setAmount(1000.0);
        incomeDTO.setDescription(null);
        incomeDTO.setDate(null);
        incomeDTO.setCategory("Salary");

        Authentication auth = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        CustomUserDetails userDetails = new CustomUserDetails(
                1L,
                "testuser",
                "password",
                true,
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
        );

        when(securityContext.getAuthentication()).thenReturn(auth);
        when(auth.isAuthenticated()).thenReturn(true);
        when(auth.getPrincipal()).thenReturn(userDetails);
        SecurityContextHolder.setContext(securityContext);

        when(incomeService.createIncome(any(IncomeDTO.class), eq("testuser"))).thenReturn(incomeDTO);


        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/incomes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(INCOME_REQUEST_BODY));


        assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
        assertEquals(INCOME_RESPONSE_BODY, result.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testCreateIncome_unauthenticated() throws Exception {

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(null);
        SecurityContextHolder.setContext(securityContext);


        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/incomes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(INCOME_REQUEST_BODY));


        assertEquals(HttpStatus.UNAUTHORIZED.value(), result.andReturn().getResponse().getStatus());
    }

    @Test
    public void testCreateIncome_invalidPrincipal() throws Exception {

        Authentication auth = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(auth);
        when(auth.isAuthenticated()).thenReturn(true);
        when(auth.getPrincipal()).thenReturn("invalid_principal");
        SecurityContextHolder.setContext(securityContext);


        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/incomes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(INCOME_REQUEST_BODY));


        assertEquals(HttpStatus.UNAUTHORIZED.value(), result.andReturn().getResponse().getStatus());
    }

    @Test
    public void testUpdateIncome_success() throws Exception {

        IncomeDTO incomeDTO = new IncomeDTO();
        incomeDTO.setId(1L);
        incomeDTO.setAmount(1000.0);
        incomeDTO.setDescription(null);
        incomeDTO.setDate(null);
        incomeDTO.setCategory("Salary");

        CustomUserDetails userDetails = new CustomUserDetails(
                1L,
                "testuser",
                "password",
                true,
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
        );

        Authentication auth = mock(Authentication.class);
        when(auth.getPrincipal()).thenReturn(userDetails);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);

        when(incomeService.updateIncome(eq(1L), any(IncomeDTO.class), eq(1L))).thenReturn(incomeDTO);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/api/incomes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(INCOME_REQUEST_BODY)
                .principal(auth));


        assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
        assertEquals(INCOME_RESPONSE_BODY, result.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testUpdateIncome_nullUser() throws Exception {

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(null);
        SecurityContextHolder.setContext(securityContext);


        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/api/incomes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(INCOME_REQUEST_BODY));

        assertEquals(HttpStatus.UNAUTHORIZED.value(), result.andReturn().getResponse().getStatus());
    }
}