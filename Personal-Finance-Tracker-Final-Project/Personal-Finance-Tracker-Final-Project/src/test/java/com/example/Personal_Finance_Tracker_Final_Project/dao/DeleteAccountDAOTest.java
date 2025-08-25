package com.example.Personal_Finance_Tracker_Final_Project.dao;

import com.example.Personal_Finance_Tracker_Final_Project.model.BlacklistedToken;
import com.example.Personal_Finance_Tracker_Final_Project.repository.BlacklistedTokenRepository;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteAccountDAOTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BlacklistedTokenRepository blacklistedTokenRepository;

    @InjectMocks
    private DeleteAccountDAO deleteAccountDAO;

    @Test
    void testAssertUserExists_whenUserFound_thenNoException() {
        Long userId = 1L;

        com.example.Personal_Finance_Tracker_Final_Project.model.User mockUser =
                new com.example.Personal_Finance_Tracker_Final_Project.model.User();
        mockUser.setId(userId);
        mockUser.setEmail("test@example.com");
        mockUser.setUsername("testUser");
        mockUser.setPassword("encodedPassword");

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        assertThatCode(() -> deleteAccountDAO.assertUserExists(userId))
                .doesNotThrowAnyException();
    }


    @Test
    void testAssertUserExists_whenUserNotFound_thenThrowException() {
        Long userId = 2L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> deleteAccountDAO.assertUserExists(userId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("User not found");
    }

    @Test
    void testBlacklistTokenSafe_whenTokenIsNullOrBlank_thenDoNothing() {
        Long userId = 3L;


        assertThatCode(() -> deleteAccountDAO.blacklistTokenSafe(null, userId))
                .doesNotThrowAnyException();

        assertThatCode(() -> deleteAccountDAO.blacklistTokenSafe("   ", userId))
                .doesNotThrowAnyException();
    }

    @Test
    void testBlacklistTokenSafe_whenValidToken_thenSaveCalled() {
        Long userId = 4L;
        String token = "valid-token";

        when(blacklistedTokenRepository.save(any(BlacklistedToken.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        assertThatCode(() -> deleteAccountDAO.blacklistTokenSafe(token, userId))
                .doesNotThrowAnyException();
    }

    @Test
    void testBlacklistTokenSafe_whenSaveThrowsDataIntegrityViolation_thenIgnoreException() {
        Long userId = 5L;
        String token = "dup-token";

        when(blacklistedTokenRepository.save(any(BlacklistedToken.class)))
                .thenThrow(new org.springframework.dao.DataIntegrityViolationException("duplicate"));

        assertThatCode(() -> deleteAccountDAO.blacklistTokenSafe(token, userId))
                .doesNotThrowAnyException();
    }

    @Test
    void testDeleteUserGraph_thenNoException() {
        Long userId = 6L;

        assertThatCode(() -> deleteAccountDAO.deleteUserGraph(userId))
                .doesNotThrowAnyException();
    }
}
