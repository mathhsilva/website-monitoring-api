package com.matheus.websitemonitoring.dto.response;

import com.matheus.websitemonitoring.enumtype.WebsiteStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class WebsiteResponse {

    private Long id;
    private String name;
    private String url;
    private Boolean active;
    private WebsiteStatus lastStatus;
    private Long lastResponseTimeMs;
    private LocalDateTime lastCheckedAt;
    private LocalDateTime createdAt;
}