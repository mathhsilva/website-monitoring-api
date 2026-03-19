package com.matheus.websitemonitoring.controller;

import com.matheus.websitemonitoring.dto.response.MonitoringRecordResponse;
import com.matheus.websitemonitoring.dto.response.WebsiteStatusResponse;
import com.matheus.websitemonitoring.service.MonitoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/websites")
@RequiredArgsConstructor
public class MonitoringController {

    private final MonitoringService monitoringService;

    @PostMapping("/{id}/check")
    public MonitoringRecordResponse checkWebsite(@PathVariable Long id) {
        return monitoringService.checkWebsite(id);
    }

    @GetMapping("/{id}/records")
    public Page<MonitoringRecordResponse> getRecords(@PathVariable Long id, Pageable pageable) {
        return monitoringService.getRecordsByWebsite(id, pageable);
    }

    @GetMapping("/{id}/status")
    public WebsiteStatusResponse getStatus(@PathVariable Long id) {
        return monitoringService.getWebsiteStatus(id);
    }
}