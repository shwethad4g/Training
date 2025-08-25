package com.example.Personal_Finance_Tracker_Final_Project.dao;

import com.example.Personal_Finance_Tracker_Final_Project.repository.BlacklistedTokenRepository;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@lombok.RequiredArgsConstructor
public class DeleteAccountDAO {

    private final UserRepository userRepository;
    private final BlacklistedTokenRepository blacklistedTokenRepository;


    public void assertUserExists(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void blacklistTokenSafe(String token, Long userId) {
        if (token == null || token.isBlank()) return;
        try {
            var bt = new com.example.Personal_Finance_Tracker_Final_Project.model.BlacklistedToken();
            bt.setToken(token);
            bt.setUserId(userId);
            blacklistedTokenRepository.save(bt);
        } catch (org.springframework.dao.DataIntegrityViolationException ignored) {

        }
    }

    public void deleteUserGraph(Long userId) {

        userRepository.deleteById(userId);
    }
}
