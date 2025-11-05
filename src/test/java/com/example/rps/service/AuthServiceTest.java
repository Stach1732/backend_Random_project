package com.example.rps.service;

import com.example.rps.model.User;
import com.example.rps.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_withNewUsername_returnsSavedUserWithHashedPassword() {
        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        User result = authService.register("admin", "1234", "mail@mail.com", 1);

        assertNotNull(result);
        assertEquals("admin", result.getUsername());
        assertTrue(result.getPassword().startsWith("$2a$")); // BCrypt hash prefix
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void login_withCorrectPassword_returnsTrue() {
        String hashed = BCrypt.hashpw("1234", BCrypt.gensalt());
        User user = new User("admin", hashed, "mail@mail.com", 1);
        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(user));

        boolean result = authService.login("admin", "1234");

        assertTrue(result);
    }

    @Test
    void login_withWrongPassword_returnsFalse() {
        String hashed = BCrypt.hashpw("1234", BCrypt.gensalt());
        User user = new User("admin", hashed, "mail@mail.com", 1);
        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(user));

        boolean result = authService.login("admin", "wrong");

        assertFalse(result);
    }

    @Test
    void login_withUnknownUser_returnsFalse() {
        when(userRepository.findByUsername("ghost")).thenReturn(Optional.empty());

        boolean result = authService.login("ghost", "1234");

        assertFalse(result);
    }
}
