package com.example.rps.controller;

import com.example.rps.model.LoginRequest;
import com.example.rps.model.User;
import com.example.rps.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerIntegrationTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private UserRepository userRepository;
    @Autowired private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
        userRepository.save(new User("admin", BCrypt.hashpw("1234", BCrypt.gensalt()), "mail@test.com", 1));
    }

    @Test
    void login_withCorrectCredentials_returns200() throws Exception {
        String body = objectMapper.writeValueAsString(new LoginRequest("admin", "1234"));
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Login OK")));
    }

    @Test
    void login_withWrongPassword_returns401() throws Exception {
        String body = objectMapper.writeValueAsString(new LoginRequest("admin", "wrong"));
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(containsString("FAIL")));
    }

    @Test
    void login_withUnknownUser_returns401() throws Exception {
        String body = objectMapper.writeValueAsString(new LoginRequest("ghost", "1234"));
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isUnauthorized());
    }
}
