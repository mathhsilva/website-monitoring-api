package com.matheus.websitemonitoring.service;

import com.matheus.websitemonitoring.dto.request.WebsiteRequest;
import com.matheus.websitemonitoring.dto.response.WebsiteResponse;

import java.util.List;

public interface WebsiteService {

    WebsiteResponse create(WebsiteRequest request);

    List<WebsiteResponse> findAll();

    WebsiteResponse findById(Long id);

    WebsiteResponse update(Long id, WebsiteRequest request);

    WebsiteResponse updateActive(Long id, Boolean active);

    void delete(Long id);
}