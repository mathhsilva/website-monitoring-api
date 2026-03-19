package com.matheus.websitemonitoring.service.impl;

import com.matheus.websitemonitoring.dto.request.WebsiteRequest;
import com.matheus.websitemonitoring.dto.response.WebsiteResponse;
import com.matheus.websitemonitoring.entity.Website;
import com.matheus.websitemonitoring.exception.BusinessException;
import com.matheus.websitemonitoring.exception.ResourceNotFoundException;
import com.matheus.websitemonitoring.repository.WebsiteRepository;
import com.matheus.websitemonitoring.service.WebsiteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebsiteServiceImpl implements WebsiteService {

    private final WebsiteRepository websiteRepository;

    @Override
    public WebsiteResponse create(WebsiteRequest request) {
        String normalizedUrl = normalizeUrl(request.getUrl());

        if (websiteRepository.existsByUrl(normalizedUrl)) {
            throw new BusinessException("Já existe um site cadastrado com essa URL");
        }

        Website website = Website.builder()
                .name(request.getName().trim())
                .url(normalizedUrl)
                .active(true)
                .build();

        Website savedWebsite = websiteRepository.save(website);
        log.info("Website created id={} url={}", savedWebsite.getId(), savedWebsite.getUrl());

        return toResponse(savedWebsite);
    }

    @Override
    public List<WebsiteResponse> findAll() {
        return websiteRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public WebsiteResponse findById(Long id) {
        return toResponse(findEntityById(id));
    }

    @Override
    public WebsiteResponse update(Long id, WebsiteRequest request) {
        Website website = findEntityById(id);
        String normalizedUrl = normalizeUrl(request.getUrl());

        if (!website.getUrl().equalsIgnoreCase(normalizedUrl) && websiteRepository.existsByUrl(normalizedUrl)) {
            throw new BusinessException("Já existe um site cadastrado com essa URL");
        }

        website.setName(request.getName().trim());
        website.setUrl(normalizedUrl);

        Website updatedWebsite = websiteRepository.save(website);
        log.info("Website updated id={} url={}", updatedWebsite.getId(), updatedWebsite.getUrl());

        return toResponse(updatedWebsite);
    }

    @Override
    public WebsiteResponse updateActive(Long id, Boolean active) {
        Website website = findEntityById(id);
        website.setActive(active);

        Website updatedWebsite = websiteRepository.save(website);
        log.info("Website active updated id={} active={}", updatedWebsite.getId(), updatedWebsite.getActive());

        return toResponse(updatedWebsite);
    }

    @Override
    public void delete(Long id) {
        Website website = findEntityById(id);
        websiteRepository.delete(website);
        log.info("Website deleted id={} url={}", website.getId(), website.getUrl());
    }

    private Website findEntityById(Long id) {
        return websiteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Site não encontrado com id: " + id));
    }

    private WebsiteResponse toResponse(Website website) {
        return WebsiteResponse.builder()
                .id(website.getId())
                .name(website.getName())
                .url(website.getUrl())
                .active(website.getActive())
                .lastStatus(website.getLastStatus())
                .lastResponseTimeMs(website.getLastResponseTimeMs())
                .lastCheckedAt(website.getLastCheckedAt())
                .createdAt(website.getCreatedAt())
                .build();
    }

    private String normalizeUrl(String url) {
        return url == null ? null : url.trim().toLowerCase();
    }
}