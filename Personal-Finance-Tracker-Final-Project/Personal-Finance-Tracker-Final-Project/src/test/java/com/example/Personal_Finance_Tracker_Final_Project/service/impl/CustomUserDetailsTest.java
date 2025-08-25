package com.example.Personal_Finance_Tracker_Final_Project.service.impl;


import com.example.Personal_Finance_Tracker_Final_Project.data_factory.CustomUserDetailsTestDataFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsTest {

    @Test
    void testGetters_whenCustomUserDetailsCreated_thenReturnCorrectValues() {
        CustomUserDetails details = CustomUserDetailsTestDataFactory.customUserDetails;

        assertThat(details.getId()).isEqualTo(1L);
        assertThat(details.getUsername()).isEqualTo("john");
        assertThat(details.getPassword()).isEqualTo("secret");
        assertThat(details.isEnabled()).isTrue();
        Collection<? extends GrantedAuthority> authorities = details.getAuthorities();
        assertThat(authorities).hasSize(1);
        assertThat(authorities.iterator().next().getAuthority()).isEqualTo("ROLE_USER");
    }

    @Test
    void testAccountNonExpired_whenCalled_thenReturnTrue() {
        CustomUserDetails details = CustomUserDetailsTestDataFactory.customUserDetails;
        assertThat(details.isAccountNonExpired()).isTrue();
    }

    @Test
    void testAccountNonLocked_whenCalled_thenReturnTrue() {
        CustomUserDetails details = CustomUserDetailsTestDataFactory.customUserDetails;
        assertThat(details.isAccountNonLocked()).isTrue();
    }

    @Test
    void testCredentialsNonExpired_whenCalled_thenReturnTrue() {
        CustomUserDetails details = CustomUserDetailsTestDataFactory.customUserDetails;
        assertThat(details.isCredentialsNonExpired()).isTrue();
    }
}
