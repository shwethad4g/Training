package com.example.Personal_Finance_Tracker_Final_Project.controller;

import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseReportDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import com.example.Personal_Finance_Tracker_Final_Project.service.ExpenseReportService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ExpenseReportControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ExpenseReportController controller;

    @Mock
    private ExpenseReportService reportService;

    @Mock
    private UserRepository userRepository;

    private static final String BASE_URL = "/api/reports/expenses";
    private static final String EMAIL = "zara@gmail.com";
    private static final long USER_ID = 42L;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
        SecurityContextHolder.clearContext();
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }


    private void authenticateWithUserDetails() {
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(EMAIL)
                .password("pw")
                .authorities("ROLE_USER")
                .build();
        var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private void authenticateWithStringPrincipal() {
        var auth = new UsernamePasswordAuthenticationToken(EMAIL, null, null);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private void authenticateAsAnonymousUserString() {
        var auth = new UsernamePasswordAuthenticationToken("anonymousUser", null, null);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private User mockPersistedUser() {
        User u = new User();
        u.setId(USER_ID);
        u.setEmail(EMAIL);
        return u;
    }

    private ExpenseReportDTO report(String period, double total, Map<String, BigDecimal> byCat) {
        ExpenseReportDTO dto = new ExpenseReportDTO();
        dto.setPeriod(period);
        dto.setTotalExpenses(BigDecimal.valueOf(total));
        if (byCat != null) dto.setExpensesByCategory(byCat);
        return dto;
    }


    @Test
    void weekly_success_userDetailsPrincipal_returns200AndBody() throws Exception {
        authenticateWithUserDetails();
        when(userRepository.findByEmailIgnoreCase(EMAIL)).thenReturn(Optional.of(mockPersistedUser()));

        Map<String, BigDecimal> cats = new LinkedHashMap<>();
        cats.put("Food", BigDecimal.valueOf(1000));
        cats.put("Travel", BigDecimal.valueOf(500));

        when(reportService.generateWeeklyReport(eq(USER_ID), eq(2025), eq(34)))
                .thenReturn(report("weekly", 1500.0, cats));

        mockMvc.perform(get(BASE_URL + "/weekly")
                        .param("year", "2025")
                        .param("week", "34")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.period").value("weekly"))
                .andExpect(jsonPath("$.totalAmount").value(1500.0))
                .andExpect(jsonPath("$.expensesByCategory.Food").value(1000))
                .andExpect(jsonPath("$.expensesByCategory.Travel").value(500));

        verify(userRepository).findByEmailIgnoreCase(EMAIL);
        verify(reportService).generateWeeklyReport(USER_ID, 2025, 34);
        verifyNoMoreInteractions(reportService, userRepository);
    }

    @Test
    void monthly_success_stringPrincipal_returns200AndBody() throws Exception {
        authenticateWithStringPrincipal();
        when(userRepository.findByEmailIgnoreCase(EMAIL)).thenReturn(Optional.of(mockPersistedUser()));

        Map<String, BigDecimal> cats = new LinkedHashMap<>();
        cats.put("Bills", BigDecimal.valueOf(3000));

        when(reportService.generateMonthlyReport(eq(USER_ID), eq(2025), eq(8)))
                .thenReturn(report("monthly", 5000.0, cats));

        mockMvc.perform(get(BASE_URL + "/monthly")
                        .param("year", "2025")
                        .param("month", "8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.period").value("monthly"))
                .andExpect(jsonPath("$.totalAmount").value(5000.0))
                .andExpect(jsonPath("$.expensesByCategory.Bills").value(3000));

        verify(userRepository).findByEmailIgnoreCase(EMAIL);
        verify(reportService).generateMonthlyReport(USER_ID, 2025, 8);
        verifyNoMoreInteractions(reportService, userRepository);
    }

    @Test
    void yearly_success_userDetailsPrincipal_returns200AndBody() throws Exception {
        authenticateWithUserDetails();
        when(userRepository.findByEmailIgnoreCase(EMAIL)).thenReturn(Optional.of(mockPersistedUser()));
        when(reportService.generateYearlyReport(eq(USER_ID), eq(2025)))
                .thenReturn(report("yearly", 25000.0, null));

        mockMvc.perform(get(BASE_URL + "/yearly")
                        .param("year", "2025")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.period").value("yearly"))
                .andExpect(jsonPath("$.totalAmount").value(25000.0));

        verify(userRepository).findByEmailIgnoreCase(EMAIL);
        verify(reportService).generateYearlyReport(USER_ID, 2025);
        verifyNoMoreInteractions(reportService, userRepository);
    }


    @Test
    void weekly_userNotFound_returns404() throws Exception {
        authenticateWithUserDetails();
        when(userRepository.findByEmailIgnoreCase(EMAIL)).thenReturn(Optional.empty());

        mockMvc.perform(get(BASE_URL + "/weekly")
                        .param("year", "2025")
                        .param("week", "34")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> Assertions.assertTrue(
                        result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> Assertions.assertTrue(
                        ((ResponseStatusException) result.getResolvedException())
                                .getReason().contains("User not found")));

        verify(userRepository).findByEmailIgnoreCase(EMAIL);
        verifyNoInteractions(reportService);
    }

    @Test
    void monthly_noAuth_returns401() throws Exception {
        SecurityContextHolder.clearContext();

        mockMvc.perform(get(BASE_URL + "/monthly")
                        .param("year", "2025")
                        .param("month", "8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

        verifyNoInteractions(userRepository, reportService);
    }

    @Test
    void yearly_anonymousUserStringPrincipal_returns401() throws Exception {
        authenticateAsAnonymousUserString();

        mockMvc.perform(get(BASE_URL + "/yearly")
                        .param("year", "2025")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

        verifyNoInteractions(userRepository, reportService);
    }
}
