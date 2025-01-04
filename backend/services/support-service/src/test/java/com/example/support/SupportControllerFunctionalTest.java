package com.example.support;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ActiveProfiles;

import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SupportControllerFunctionalTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateTicket() throws Exception {
        String credentials = Base64.getEncoder().encodeToString("testUser:testPassword".getBytes());

        mockMvc.perform(post("/support/tickets")
                .header(HttpHeaders.AUTHORIZATION, "Basic " + credentials)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":1,\"subject\":\"Test Ticket\",\"description\":\"Description\"}"))
            .andExpect(status().isOk());
    }

    @Test
    public void testGetAllTickets() throws Exception {
        mockMvc.perform(get("/support/tickets")
                .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString("testUser:testPassword".getBytes())))
            .andExpect(status().isOk());
    }
}
