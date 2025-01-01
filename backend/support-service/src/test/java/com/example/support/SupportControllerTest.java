package com.example.support;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.http.HttpHeaders;
import java.util.Base64;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SupportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllTickets() throws Exception {
        mockMvc.perform(get("/support/tickets")
                .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString("testUser:testPassword".getBytes())))
            .andExpect(status().isOk());
    }

    @Test
    public void testCreateTicket() throws Exception {
        mockMvc.perform(post("/support/tickets")
                .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString("testUser:testPassword".getBytes()))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"user_id\":1,\"subject\":\"Issue with login\",\"description\":\"I cannot log into my account.\"}"))
            .andExpect(status().isOk());
    }

}
