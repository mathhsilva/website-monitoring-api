package com.matheus.websitemonitoring.dto.response;

import com.matheus.websitemonitoring.enumtype.WebsiteStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MonitoringRecordResponse {

    private Long id;
    private Long websiteId;
    private String websiteName;
    private String url;
    private WebsiteStatus status;
    private Long responseTimeMs;
    private Integer httpStatusCode;
    private String errorMessage;
    private LocalDateTime checkedAt;
}