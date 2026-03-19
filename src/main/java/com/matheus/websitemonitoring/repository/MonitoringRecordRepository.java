package com.matheus.websitemonitoring.repository;

import com.matheus.websitemonitoring.entity.MonitoringRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitoringRecordRepository extends JpaRepository<MonitoringRecord, Long> {

    Page<MonitoringRecord> findByWebsiteIdOrderByCheckedAtDesc(Long websiteId, Pageable pageable);
}