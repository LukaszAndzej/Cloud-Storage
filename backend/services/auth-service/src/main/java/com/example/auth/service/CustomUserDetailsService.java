package com.example.auth.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final JdbcTemplate jdbcTemplate;

    public CustomUserDetailsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return jdbcTemplate.queryForObject(
                "SELECT username, password FROM users WHERE username = ?",
                (rs, rowNum) -> User.builder()
                    .username(rs.getString("username"))
                    .password(rs.getString("password")) // To jest zakodowane hasło z bazy
                    .roles("USER")
                    .build(),
                username
            );
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
