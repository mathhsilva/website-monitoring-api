package com.matheus.websitemonitoring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matheus.websitemonitoring.dto.request.WebsiteRequest;
import com.matheus.websitemonitoring.dto.response.WebsiteResponse;
import com.matheus.websitemonitoring.enumtype.WebsiteStatus;
import com.matheus.websitemonitoring.service.WebsiteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WebsiteController.class)
class WebsiteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private WebsiteService websiteService;

    @Test
    void shouldCreateWebsite() throws Exception {
        WebsiteRequest request = new WebsiteRequest();
        request.setName("Google");
        request.setUrl("https://www.google.com");

        WebsiteResponse response = WebsiteResponse.builder()
                .id(1L)
                .name("Google")
                .url("https://www.google.com")
                .active(true)
                .lastStatus(WebsiteStatus.UP)
                .lastResponseTimeMs(120L)
                .lastCheckedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();

        when(websiteService.create(any(WebsiteRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/websites")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Google"))
                .andExpect(jsonPath("$.url").value("https://www.google.com"));
    }

    @Test
    void shouldListWebsites() throws Exception {
        WebsiteResponse response = WebsiteResponse.builder()
                .id(1L)
                .name("Google")
                .url("https://www.google.com")
                .active(true)
                .build();

        when(websiteService.findAll()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/websites"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Google"));
    }

    @Test
    void shouldReturnBadRequestWhenPayloadIsInvalid() throws Exception {
        WebsiteRequest request = new WebsiteRequest();
        request.setName("");
        request.setUrl("google.com");

        mockMvc.perform(post("/api/websites")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}