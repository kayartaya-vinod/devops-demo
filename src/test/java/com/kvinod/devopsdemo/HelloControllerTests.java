package com.kvinod.devopsdemo;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTests {

    @Autowired
    HelloController controller;

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldGetHelloController() {
        assertNotNull(controller);
    }

    @Test
    void shouldGetDefaultHelloMessage() throws Exception {
        mockMvc.perform(get("/api/hello")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, friend!")));
    }

    @Test
    void shouldGetCustomHelloMessage() throws Exception {
        mockMvc.perform(get("/api/hello?name=Vinod")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, Vinod!")));
    }

}
