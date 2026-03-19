package com.matheus.websitemonitoring.scheduler;

import com.matheus.websitemonitoring.entity.Website;
import com.matheus.websitemonitoring.repository.WebsiteRepository;
import com.matheus.websitemonitoring.service.MonitoringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebsiteMonitoringScheduler {

    private final WebsiteRepository websiteRepository;
    private final MonitoringService monitoringService;

    @Scheduled(fixedRateString = "${monitoring.scheduler.fixed-rate}")
    public void execute() {
        List<Website> activeWebsites = websiteRepository.findByActiveTrue();

        if (activeWebsites.isEmpty()) {
            log.info("No active websites found for monitoring");
            return;
        }

        log.info("Starting scheduled monitoring for {} website(s)", activeWebsites.size());

        for (Website website : activeWebsites) {
            try {
                monitoringService.checkWebsite(website.getId());
            } catch (Exception ex) {
                log.error("Error checking website id={} url={} message={}",
                        website.getId(),
                        website.getUrl(),
                        ex.getMessage());
            }
        }

        log.info("Scheduled monitoring finished");
    }
}