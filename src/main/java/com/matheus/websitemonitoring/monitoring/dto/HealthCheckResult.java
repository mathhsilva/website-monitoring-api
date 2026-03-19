package com.matheus.websitemonitoring.monitoring.dto;

import com.matheus.websitemonitoring.enumtype.WebsiteStatus;

import java.time.LocalDateTime;

public record HealthCheckResult(
        WebsiteStatus status,
        Integer httpStatus,
        Long responseTimeMs,
        String errorMessage,
        LocalDateTime checkedAt
) {}