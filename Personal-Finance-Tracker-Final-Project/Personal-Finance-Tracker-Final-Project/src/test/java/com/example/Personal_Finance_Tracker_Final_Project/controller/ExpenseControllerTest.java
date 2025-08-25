package com.example.Personal_Finance_Tracker_Final_Project.controller;

import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.exception.GlobalExceptionHandler;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import com.example.Personal_Finance_Tracker_Final_Project.service.ExpenseService;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class ExpenseControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ExpenseController controller;

    @Mock
    private ExpenseService expenseService;

    @Mock
    private UserRepository userRepository;

    private AutoCloseable mocks;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String BASE_URL = "/api/expenses";
    private static final String EMAIL = "zara@gmail.com";
    private static final long USER_ID = 42L;

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
    void createExpense_success_returns200AndBody() throws Exception {

        User user = new User();
        user.setId(USER_ID);
        user.setEmail(EMAIL);
        when(userRepository.findByEmailIgnoreCase(EMAIL)).thenReturn(Optional.of(user));


        ExpenseDTO req = new ExpenseDTO();
        req.setAmount(BigDecimal.valueOf(100.0));
        req.setDescription("Groceries");

        ExpenseDTO created = new ExpenseDTO();
        created.setId(1L);
        created.setAmount(BigDecimal.valueOf(100.0));
        created.setDescription("Groceries");

        when(expenseService.createExpense(any(ExpenseDTO.class), eq(USER_ID))).thenReturn(created);

        String jsonReq = objectMapper.writeValueAsString(req);
        String expectedJson = objectMapper.writeValueAsString(created);

        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonReq)
                        .accept(MediaType.APPLICATION_JSON)
        );


        Assertions.assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
        Assertions.assertEquals(expectedJson, result.andReturn().getResponse().getContentAsString());
    }

    @Test
    void updateExpense_success_returns200AndBody() throws Exception {

        Long expenseId = 99L;

        User user = new User();
        user.setId(USER_ID);
        user.setEmail(EMAIL);
        when(userRepository.findByEmailIgnoreCase(EMAIL)).thenReturn(Optional.of(user));

        ExpenseDTO req = new ExpenseDTO();
        req.setAmount(BigDecimal.valueOf(200.0));
        req.setDescription("Updated Rent");

        ExpenseDTO updated = new ExpenseDTO();
        updated.setId(expenseId);
        updated.setAmount(BigDecimal.valueOf(200.0));
        updated.setDescription("Updated Rent");

        when(expenseService.updateExpense(eq(expenseId), any(ExpenseDTO.class), eq(USER_ID)))
                .thenReturn(updated);

        String jsonReq = objectMapper.writeValueAsString(req);
        String expectedJson = objectMapper.writeValueAsString(updated);

        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.put(BASE_URL + "/" + expenseId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonReq)
                        .accept(MediaType.APPLICATION_JSON)
        );

        Assertions.assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
        Assertions.assertEquals(expectedJson, result.andReturn().getResponse().getContentAsString());
    }

    @Test
    void createExpense_userNotFound_returns404() throws Exception {

        when(userRepository.findByEmailIgnoreCase(EMAIL)).thenReturn(Optional.empty());
        when(userRepository.findByUsernameIgnoreCase(EMAIL)).thenReturn(Optional.empty());

        ExpenseDTO req = new ExpenseDTO();
        req.setAmount(BigDecimal.valueOf(50.0));
        req.setDescription("Snacks");

        String jsonReq = objectMapper.writeValueAsString(req);

        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonReq)
                        .accept(MediaType.APPLICATION_JSON)
        );


        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), result.andReturn().getResponse().getStatus());
        Assertions.assertTrue(
                result.andReturn().getResponse().getContentAsString().contains("User not found for: " + EMAIL)
        );
    }
}
