package com.matheus.websitemonitoring.service.impl;

import com.matheus.websitemonitoring.dto.response.MonitoringRecordResponse;
import com.matheus.websitemonitoring.dto.response.WebsiteStatusResponse;
import com.matheus.websitemonitoring.entity.MonitoringRecord;
import com.matheus.websitemonitoring.entity.Website;
import com.matheus.websitemonitoring.exception.ResourceNotFoundException;
import com.matheus.websitemonitoring.monitoring.WebsiteHealthChecker;
import com.matheus.websitemonitoring.monitoring.dto.HealthCheckResult;
import com.matheus.websitemonitoring.repository.MonitoringRecordRepository;
import com.matheus.websitemonitoring.repository.WebsiteRepository;
import com.matheus.websitemonitoring.service.MonitoringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MonitoringServiceImpl implements MonitoringService {

    private final WebsiteRepository websiteRepository;
    private final MonitoringRecordRepository monitoringRecordRepository;
    private final WebsiteHealthChecker websiteHealthChecker;

    @Override
    @Transactional
    public MonitoringRecordResponse checkWebsite(Long websiteId) {
        Website website = findWebsiteById(websiteId);

        log.info("Starting monitoring check websiteId={} url={}", website.getId(), website.getUrl());

        HealthCheckResult result = websiteHealthChecker.check(website.getUrl());

        MonitoringRecord record = MonitoringRecord.builder()
                .website(website)
                .status(result.status())
                .responseTimeMs(result.responseTimeMs())
                .httpStatusCode(result.httpStatus())
                .errorMessage(result.errorMessage())
                .checkedAt(result.checkedAt())
                .build();

        MonitoringRecord savedRecord = monitoringRecordRepository.save(record);

        website.setLastStatus(result.status());
        website.setLastResponseTimeMs(result.responseTimeMs());
        website.setLastCheckedAt(result.checkedAt());

        websiteRepository.save(website);

        log.info(
                "Finished monitoring check websiteId={} status={} responseTimeMs={} httpStatusCode={}",
                website.getId(),
                result.status(),
                result.responseTimeMs(),
                result.httpStatus()
        );

        return toRecordResponse(savedRecord);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MonitoringRecordResponse> getRecordsByWebsite(Long websiteId, Pageable pageable) {
        validateWebsiteExists(websiteId);

        return monitoringRecordRepository.findByWebsiteIdOrderByCheckedAtDesc(websiteId, pageable)
                .map(this::toRecordResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public WebsiteStatusResponse getWebsiteStatus(Long websiteId) {
        Website website = findWebsiteById(websiteId);
        return toStatusResponse(website);
    }

    private Website findWebsiteById(Long websiteId) {
        return websiteRepository.findById(websiteId)
                .orElseThrow(() -> new ResourceNotFoundException("Site não encontrado com id: " + websiteId));
    }

    private void validateWebsiteExists(Long websiteId) {
        if (!websiteRepository.existsById(websiteId)) {
            throw new ResourceNotFoundException("Site não encontrado com id: " + websiteId);
        }
    }

    private MonitoringRecordResponse toRecordResponse(MonitoringRecord record) {
        return MonitoringRecordResponse.builder()
                .id(record.getId())
                .websiteId(record.getWebsite().getId())
                .websiteName(record.getWebsite().getName())
                .url(record.getWebsite().getUrl())
                .status(record.getStatus())
                .responseTimeMs(record.getResponseTimeMs())
                .httpStatusCode(record.getHttpStatusCode())
                .errorMessage(record.getErrorMessage())
                .checkedAt(record.getCheckedAt())
                .build();
    }

    private WebsiteStatusResponse toStatusResponse(Website website) {
        return WebsiteStatusResponse.builder()
                .websiteId(website.getId())
                .websiteName(website.getName())
                .url(website.getUrl())
                .active(website.getActive())
                .lastStatus(website.getLastStatus())
                .lastResponseTimeMs(website.getLastResponseTimeMs())
                .lastCheckedAt(website.getLastCheckedAt())
                .build();
    }
}