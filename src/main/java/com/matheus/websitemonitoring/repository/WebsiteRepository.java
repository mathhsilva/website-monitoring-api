package com.matheus.websitemonitoring.repository;

import com.matheus.websitemonitoring.entity.Website;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WebsiteRepository extends JpaRepository<Website, Long> {
    Optional<Website> findByUrl(String url);
    boolean existsByUrl(String url);
    List<Website> findByActiveTrue();
}