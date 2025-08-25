package com.example.Personal_Finance_Tracker_Final_Project.controller;

import com.example.Personal_Finance_Tracker_Final_Project.dto.LogoutResponseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.service.LogoutService;
import com.example.Personal_Finance_Tracker_Final_Project.util.JwtUtil;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class LogoutControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private LogoutController logoutController;

    @Mock
    private LogoutService logoutService;

    @Mock
    private JwtUtil jwtUtil;

    private static final String LOGOUT_RESPONSE_BODY = "{\"message\":\"Successfully logged out\"}";

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(logoutController).build();
    }

    @Test
    public void testLogout_whenAuthorizationHeaderMissing_thenReturnUnauthorized() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(
                "/api/auth/logout"
        ).contentType(MediaType.APPLICATION_JSON));

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), result.andReturn().getResponse().getStatus());
    }

    @Test
    public void testLogout_whenInvalidBearerToken_thenReturnUnauthorized() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(
                        "/api/auth/logout"
                ).contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "InvalidToken"));

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), result.andReturn().getResponse().getStatus());
    }

    @Test
    public void testLogout_whenUserIdNull_thenReturnUnauthorized() throws Exception {
        String token = "jwt-token";
        when(jwtUtil.extractUserId(token)).thenReturn(null);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(
                        "/api/auth/logout"
                ).contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token));

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), result.andReturn().getResponse().getStatus());
    }

    @Test
    public void testLogout_whenValidToken_thenReturnOkResponse() throws Exception {
        String token = "jwt-token";
        Long userId = 1L;
        LogoutResponseDTO responseDTO = new LogoutResponseDTO();
        responseDTO.setMessage("Successfully logged out");

        when(jwtUtil.extractUserId(token)).thenReturn(userId);
        when(logoutService.logout(anyLong(), anyString())).thenReturn(responseDTO);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(
                        "/api/auth/logout"
                ).contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token));

        Assertions.assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
        Assertions.assertEquals(LOGOUT_RESPONSE_BODY,
                result.andReturn().getResponse().getContentAsString());
    }
}
