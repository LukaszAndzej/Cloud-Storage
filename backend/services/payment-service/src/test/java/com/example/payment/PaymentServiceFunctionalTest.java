package com.example.payment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PaymentServiceFunctionalTest {

    @LocalServerPort
    private int port;

    @Autowired
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    void testGetPayments() {
        given()
            .when()
            .get("/payments")
            .then()
            .statusCode(200)
            .body("[0]", equalTo("Payment1"))
            .body("[1]", equalTo("Payment2"));
    }

    @Test
    void testCreatePayment() {
        String json = """
            {
                "userId": 1,
                "amount": 100.50,
                "status": "PENDING"
            }
        """;

        given()
            .contentType("application/json")
            .body(json)
            .when()
            .post("/payments")
            .then()
            .statusCode(200)
            .body(equalTo("Payment created"));
    }
}
