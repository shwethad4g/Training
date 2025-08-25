
package com.example.Personal_Finance_Tracker_Final_Project.dao;

import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDAOTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserDAO userDAO;

    @Test
    void testFindByEmail_whenUserExists_thenReturnUser() {
        User u = new User();
        u.setId(1L);
        u.setEmail("user@example.com");
        when(userRepository.findByEmailIgnoreCase("user@example.com")).thenReturn(Optional.of(u));

        User result = userDAO.findByEmail("user@example.com");

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("user@example.com");
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void testFindByEmail_whenUserNotFound_thenReturnNull() {
        when(userRepository.findByEmailIgnoreCase("missing@example.com")).thenReturn(Optional.empty());

        User result = userDAO.findByEmail("missing@example.com");

        assertThat(result).isNull();
    }

    @Test
    void testSaveUser_whenPasswordPresent_thenEncodeAndSave() {
        User toSave = new User();
        toSave.setEmail("a@b.com");
        toSave.setPassword("plain");

        User saved = new User();
        saved.setId(10L);
        saved.setEmail("a@b.com");
        saved.setPassword("encoded");

        when(passwordEncoder.encode("plain")).thenReturn("encoded");
        when(userRepository.save(any(User.class))).thenReturn(saved);

        User result = userDAO.saveUser(toSave);

        assertThat(result.getId()).isEqualTo(10L);
        assertThat(result.getEmail()).isEqualTo("a@b.com");
        assertThat(result.getPassword()).isEqualTo("encoded");
    }

    @Test
    void testSaveUser_whenPasswordNull_thenSaveWithoutEncoding() {
        User toSave = new User();
        toSave.setEmail("x@y.com");
        toSave.setPassword(null);

        User saved = new User();
        saved.setId(11L);
        saved.setEmail("x@y.com");
        saved.setPassword(null);

        when(userRepository.save(any(User.class))).thenReturn(saved);

        User result = userDAO.saveUser(toSave);

        assertThat(result.getId()).isEqualTo(11L);
        assertThat(result.getEmail()).isEqualTo("x@y.com");
        assertThat(result.getPassword()).isNull();
    }
}

