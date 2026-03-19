package com.matheus.websitemonitoring.service;

import com.matheus.websitemonitoring.dto.response.MonitoringRecordResponse;
import com.matheus.websitemonitoring.entity.MonitoringRecord;
import com.matheus.websitemonitoring.entity.Website;
import com.matheus.websitemonitoring.enumtype.WebsiteStatus;
import com.matheus.websitemonitoring.monitoring.WebsiteHealthChecker;
import com.matheus.websitemonitoring.monitoring.dto.HealthCheckResult;
import com.matheus.websitemonitoring.repository.MonitoringRecordRepository;
import com.matheus.websitemonitoring.repository.WebsiteRepository;
import com.matheus.websitemonitoring.service.impl.MonitoringServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MonitoringServiceImplTest {

    @Mock
    private WebsiteRepository websiteRepository;

    @Mock
    private MonitoringRecordRepository monitoringRecordRepository;

    @Mock
    private WebsiteHealthChecker websiteHealthChecker;

    @InjectMocks
    private MonitoringServiceImpl monitoringService;

    @Test
    void shouldCheckWebsiteAndSaveMonitoringRecord() {
        Website website = Website.builder()
                .id(1L)
                .name("Google")
                .url("https://www.google.com")
                .active(true)
                .build();

        HealthCheckResult result = new HealthCheckResult(
                WebsiteStatus.UP,
                200,
                150L,
                null,
                LocalDateTime.now()
        );

        MonitoringRecord savedRecord = MonitoringRecord.builder()
                .id(10L)
                .website(website)
                .status(WebsiteStatus.UP)
                .responseTimeMs(150L)
                .httpStatusCode(200)
                .checkedAt(result.checkedAt())
                .build();

        when(websiteRepository.findById(1L)).thenReturn(Optional.of(website));
        when(websiteHealthChecker.check("https://www.google.com")).thenReturn(result);
        when(monitoringRecordRepository.save(any(MonitoringRecord.class))).thenReturn(savedRecord);
        when(websiteRepository.save(any(Website.class))).thenReturn(website);

        MonitoringRecordResponse response = monitoringService.checkWebsite(1L);

        assertNotNull(response);
        assertEquals(10L, response.getId());
        assertEquals(WebsiteStatus.UP, response.getStatus());
        assertEquals(150L, response.getResponseTimeMs());
        assertEquals(200, response.getHttpStatusCode());

        verify(monitoringRecordRepository).save(any(MonitoringRecord.class));
        verify(websiteRepository).save(any(Website.class));
    }
}