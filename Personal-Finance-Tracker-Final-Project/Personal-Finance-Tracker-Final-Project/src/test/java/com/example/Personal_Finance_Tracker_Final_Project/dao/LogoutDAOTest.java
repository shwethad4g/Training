package com.example.Personal_Finance_Tracker_Final_Project.dao;

import com.example.Personal_Finance_Tracker_Final_Project.model.BlacklistedToken;
import com.example.Personal_Finance_Tracker_Final_Project.repository.BlacklistedTokenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LogoutDAOTest {

    @Mock
    private BlacklistedTokenRepository blacklistedTokenRepository;

    @InjectMocks
    private LogoutDAO logoutDAO;

    @Test
    void testBlacklistToken_whenValidInput_thenNoException() {
        when(blacklistedTokenRepository.save(any(BlacklistedToken.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        assertThatCode(() -> logoutDAO.blacklistToken("token-123", 1L))
                .doesNotThrowAnyException();
    }

    @Test
    void testIsTokenBlacklisted_whenExists_thenReturnTrue() {
        when(blacklistedTokenRepository.existsByToken("blk-token")).thenReturn(true);

        boolean result = logoutDAO.isTokenBlacklisted("blk-token");

        assertThat(result).isTrue();
    }

    @Test
    void testIsTokenBlacklisted_whenNotExists_thenReturnFalse() {
        when(blacklistedTokenRepository.existsByToken("free-token")).thenReturn(false);

        boolean result = logoutDAO.isTokenBlacklisted("free-token");

        assertThat(result).isFalse();
    }
}
