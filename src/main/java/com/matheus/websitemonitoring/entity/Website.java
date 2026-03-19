package com.matheus.websitemonitoring.entity;

import com.matheus.websitemonitoring.enumtype.WebsiteStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "websites")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Website {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, unique = true, length = 300)
    private String url;

    @Column(nullable = false)
    private Boolean active;

    @Enumerated(EnumType.STRING)
    @Column(name = "last_status", length = 10)
    private WebsiteStatus lastStatus;

    @Column(name = "last_response_time_ms")
    private Long lastResponseTimeMs;

    @Column(name = "last_checked_at")
    private LocalDateTime lastCheckedAt;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (this.active == null) {
            this.active = true;
        }
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}