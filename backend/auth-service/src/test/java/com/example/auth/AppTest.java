package com.example.auth;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.auth.repository.UserRepository;
import com.example.auth.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AppTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    public void setup() {
        System.out.println("### Wykonywanie setup() ###");
        userRepository = Mockito.mock(UserRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    public void testUserServiceInitialization() {
        System.out.println("### Wykonywanie testUserServiceInitialization() ###");
        assertNotNull(userService, "UserService powinien być zainicjalizowany.");
    }
}
