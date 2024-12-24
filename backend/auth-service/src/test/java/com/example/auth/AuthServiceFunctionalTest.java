package com.example.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test") // Wskazanie profilu testowego
public class AuthServiceFunctionalTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void resetDatabase() {
        // Usuń dynamicznych użytkowników
        jdbcTemplate.execute("DELETE FROM app_user WHERE username = 'new_user'");

        // Dodaj stałego użytkownika testowego
        String encodedPassword = passwordEncoder.encode("password123");
        jdbcTemplate.update(
            "INSERT INTO app_user (username, password) VALUES ('test_user', ?) ON CONFLICT DO NOTHING",
            encodedPassword
        );
    }

    @Test
    public void testUserLoginSuccess() {
        given()
            .contentType("application/json")
            .body("{\"username\": \"test_user\", \"password\": \"password123\"}") // Przekazanie danych logowania w JSON
        .when()
            .post("http://localhost:8081/auth/login")
        .then()
            .statusCode(200)
            .body("username", equalTo("test_user")); // Sprawdzenie odpowiedzi
    }

    @Test
    public void testUserLoginFailure() {
        given()
            .auth()
            .basic("invalid_user", "invalid_password")
        .when()
            .get("http://localhost:8081/auth/login")
        .then()
            .statusCode(401);
    }

    @Test
    public void testUserRegistration() {
        given()
            .contentType("application/json") // Dodano Content-Type
            .body("{\"username\": \"new_user\", \"password\": \"new_password\"}")
        .when()
            .post("http://localhost:8081/auth/register")
        .then()
            .statusCode(201)
            .body("username", equalTo("new_user"));
    }
}
