package com.example.Personal_Finance_Tracker_Final_Project.controller;


import com.example.Personal_Finance_Tracker_Final_Project.dto.LoginRequestDTO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.LoginResponseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.SignupRequestDTO;
import com.example.Personal_Finance_Tracker_Final_Project.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class AuthControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthServiceImpl authService;


    private static final String SIGNUP_REQUEST_BODY =
            "{\"username\":\"zara\",\"email\":\"zara@gmail.com\",\"password\":\"Secret123!\"}";

    private static final String LOGIN_REQUEST_BODY =
            "{\"username\":\"zara@gmail.com\",\"password\":\"Secret123!\"}";

    private static final String SIGNUP_EXPECTED_BODY =
            "{\"message\":\"Signup successful\"}";


    private static final String LOGIN_EXPECTED_BODY =
            "{\"id\":1,\"username\":\"zara\",\"email\":\"zara@gmail.com\",\"token\":\"jwt-token-123\"}";

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void signup_returns200_andMessage() throws Exception {

        doNothing().when(authService).signup(any(SignupRequestDTO.class));

        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(SIGNUP_REQUEST_BODY)
        );

        Assertions.assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
        Assertions.assertEquals(SIGNUP_EXPECTED_BODY, result.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void login_returns200_andLoginResponse() throws Exception {

        LoginResponseDTO mockResp = new LoginResponseDTO(1L, "zara",
                "zara@gmail.com", "jwt-token-123");
        when(authService.login(any(LoginRequestDTO.class))).thenReturn(mockResp);

        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(LOGIN_REQUEST_BODY)
        );

        Assertions.assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
        Assertions.assertEquals(LOGIN_EXPECTED_BODY, result.andReturn().getResponse().getContentAsString());
    }
}
