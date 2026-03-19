package com.matheus.websitemonitoring.monitoring;

import com.matheus.websitemonitoring.enumtype.WebsiteStatus;
import com.matheus.websitemonitoring.monitoring.dto.HealthCheckResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;

@Component
public class WebsiteHealthChecker {

    private final RestTemplate restTemplate;

    public WebsiteHealthChecker(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public HealthCheckResult check(String url) {
        long start = System.currentTimeMillis();

        try {
            URI uri = URI.create(url);

            ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

            long duration = System.currentTimeMillis() - start;

            return new HealthCheckResult(
                    WebsiteStatus.UP,
                    response.getStatusCode().value(),
                    duration,
                    null,
                    LocalDateTime.now()
            );

        } catch (Exception e) {
            long duration = System.currentTimeMillis() - start;

            return new HealthCheckResult(
                    WebsiteStatus.DOWN,
                    null,
                    duration,
                    e.getMessage(),
                    LocalDateTime.now()
            );
        }
    }
}