package com.example.auth;

import io.restassured.RestAssured;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "spring.main.web-application-type=servlet")
@ActiveProfiles("test") // Wskazanie profilu testowego
public class AuthServiceFunctionalTest {

    @LocalServerPort
    private int port;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        System.out.println("Running tests on port: " + port);
    }

    @BeforeEach
    public void resetDatabase() {
        jdbcTemplate.execute("DELETE FROM users WHERE username = 'test_user'");

        String password = "password123";
        String encodedPassword = passwordEncoder.encode(password);

        jdbcTemplate.update(
            "INSERT INTO users (username, password) VALUES (?, ?)",
            "test_user", encodedPassword
        );
    }

    @Test
    public void testUserLoginSuccess() {
        given()
            .contentType("application/json")
            .body("{\"username\": \"test_user\", \"password\": \"password123\"}")
        .when()
            .post("/auth/login")
        .then()
            .log().all()
            .statusCode(200)
            .body("username", equalTo("test_user"));
    }

    @Test
    public void testUserLoginFailure() {
        given()
            .contentType("application/json")
            .body("{\"username\": \"invalid_user\", \"password\": \"invalid_password\"}")
        .when()
            .post("/auth/login")
        .then()
            .statusCode(401);
    }

    @Test
    public void testUserRegistration() {
        given()
            .contentType("application/json")
            .body("{\"username\": \"new_user\", \"password\": \"new_password\"}")
        .when()
            .post("/auth/register")
        .then()
            .statusCode(201)
            .body("username", equalTo("new_user"));
    }
}
