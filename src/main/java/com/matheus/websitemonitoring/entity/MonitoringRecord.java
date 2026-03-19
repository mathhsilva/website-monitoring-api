package com.matheus.websitemonitoring.entity;

import com.matheus.websitemonitoring.enumtype.WebsiteStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "monitoring_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonitoringRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "website_id", nullable = false)
    private Website website;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private WebsiteStatus status;

    @Column(name = "response_time_ms")
    private Long responseTimeMs;

    @Column(name = "http_status_code")
    private Integer httpStatusCode;

    @Column(name = "error_message", length = 1000)
    private String errorMessage;

    @Column(nullable = false, updatable = false)
    private LocalDateTime checkedAt;

    @PrePersist
    public void prePersist() {
        if (this.checkedAt == null) {
            this.checkedAt = LocalDateTime.now();
        }
    }
}