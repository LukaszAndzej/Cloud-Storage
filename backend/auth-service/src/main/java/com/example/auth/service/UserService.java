package com.example.auth.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth.model.User;
import com.example.auth.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Rejestracja użytkownika
     */
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Kodowanie hasła
        return userRepository.save(user);
    }

    /**
     * Logowanie użytkownika
     */
    public boolean login(String username, String password) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }

    /**
     * Rejestracja użytkownika na podstawie danych
     */
    public void registerUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // Kodowanie hasła
        userRepository.save(user);
    }

    /**
     * Znajdowanie użytkownika po nazwie użytkownika
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Usuwanie użytkownika na podstawie nazwy użytkownika
     */
    public void deleteUser(String username) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        user.ifPresent(userRepository::delete);
    }

    /**
     * Uwierzytelnianie użytkownika
     */
    public boolean authenticate(String username, String password) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }

    /**
     * Resetowanie bazy danych
     */
    public void resetDatabase() {
        userRepository.deleteAll();
    }
}
