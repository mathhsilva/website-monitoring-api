package com.matheus.websitemonitoring.service;

import com.matheus.websitemonitoring.dto.response.MonitoringRecordResponse;
import com.matheus.websitemonitoring.dto.response.WebsiteStatusResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MonitoringService {

    MonitoringRecordResponse checkWebsite(Long websiteId);

    Page<MonitoringRecordResponse> getRecordsByWebsite(Long websiteId, Pageable pageable);

    WebsiteStatusResponse getWebsiteStatus(Long websiteId);
}