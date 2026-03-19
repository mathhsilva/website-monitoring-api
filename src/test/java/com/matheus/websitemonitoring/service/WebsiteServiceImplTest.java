package com.matheus.websitemonitoring.service;

import com.matheus.websitemonitoring.dto.request.WebsiteRequest;
import com.matheus.websitemonitoring.dto.response.WebsiteResponse;
import com.matheus.websitemonitoring.entity.Website;
import com.matheus.websitemonitoring.exception.BusinessException;
import com.matheus.websitemonitoring.exception.ResourceNotFoundException;
import com.matheus.websitemonitoring.repository.WebsiteRepository;
import com.matheus.websitemonitoring.service.impl.WebsiteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WebsiteServiceImplTest {

    @Mock
    private WebsiteRepository websiteRepository;

    @InjectMocks
    private WebsiteServiceImpl websiteService;

    private WebsiteRequest request;

    @BeforeEach
    void setUp() {
        request = new WebsiteRequest();
        request.setName("Google");
        request.setUrl("https://www.google.com");
    }

    @Test
    void shouldCreateWebsiteSuccessfully() {
        Website saved = Website.builder()
                .id(1L)
                .name("Google")
                .url("https://www.google.com")
                .active(true)
                .build();

        when(websiteRepository.existsByUrl("https://www.google.com")).thenReturn(false);
        when(websiteRepository.save(any(Website.class))).thenReturn(saved);

        WebsiteResponse response = websiteService.create(request);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Google", response.getName());
        assertEquals("https://www.google.com", response.getUrl());
        assertTrue(response.getActive());

        verify(websiteRepository).existsByUrl("https://www.google.com");
        verify(websiteRepository).save(any(Website.class));
    }

    @Test
    void shouldThrowBusinessExceptionWhenUrlAlreadyExists() {
        when(websiteRepository.existsByUrl("https://www.google.com")).thenReturn(true);

        assertThrows(BusinessException.class, () -> websiteService.create(request));

        verify(websiteRepository).existsByUrl("https://www.google.com");
        verify(websiteRepository, never()).save(any());
    }

    @Test
    void shouldFindWebsiteById() {
        Website website = Website.builder()
                .id(1L)
                .name("Google")
                .url("https://www.google.com")
                .active(true)
                .build();

        when(websiteRepository.findById(1L)).thenReturn(Optional.of(website));

        WebsiteResponse response = websiteService.findById(1L);

        assertEquals(1L, response.getId());
        assertEquals("Google", response.getName());
    }

    @Test
    void shouldThrowNotFoundWhenWebsiteDoesNotExist() {
        when(websiteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> websiteService.findById(99L));
    }
}