package com.matheus.websitemonitoring.controller;

import com.matheus.websitemonitoring.dto.request.WebsiteActiveRequest;
import com.matheus.websitemonitoring.dto.request.WebsiteRequest;
import com.matheus.websitemonitoring.dto.response.WebsiteResponse;
import com.matheus.websitemonitoring.service.WebsiteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/websites")
@RequiredArgsConstructor
public class WebsiteController {

    private final WebsiteService websiteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WebsiteResponse create(@Valid @RequestBody WebsiteRequest request) {
        return websiteService.create(request);
    }

    @GetMapping
    public List<WebsiteResponse> findAll() {
        return websiteService.findAll();
    }

    @GetMapping("/{id}")
    public WebsiteResponse findById(@PathVariable Long id) {
        return websiteService.findById(id);
    }

    @PutMapping("/{id}")
    public WebsiteResponse update(@PathVariable Long id, @Valid @RequestBody WebsiteRequest request) {
        return websiteService.update(id, request);
    }

    @PatchMapping("/{id}/active")
    public WebsiteResponse updateActive(@PathVariable Long id, @Valid @RequestBody WebsiteActiveRequest request) {
        return websiteService.updateActive(id, request.getActive());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        websiteService.delete(id);
    }
}