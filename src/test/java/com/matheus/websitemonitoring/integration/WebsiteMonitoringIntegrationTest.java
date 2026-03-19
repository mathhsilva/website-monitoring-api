package com.matheus.websitemonitoring.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matheus.websitemonitoring.dto.request.WebsiteRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = {
        "spring.task.scheduling.enabled=false"
})
@AutoConfigureMockMvc
class WebsiteMonitoringIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateWebsiteInIntegrationTest() throws Exception {
        WebsiteRequest request = new WebsiteRequest();
        request.setName("Google");
        request.setUrl("https://www.google.com");

        mockMvc.perform(post("/api/websites")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Google"))
                .andExpect(jsonPath("$.url").value("https://www.google.com"));
    }
}