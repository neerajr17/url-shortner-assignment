package com.infracloud.assignment.url.shortner.service;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
public interface UrlShortnerService {

    String shortenUrl(@NotBlank String url);

    String getUrlByKey(@NotBlank String key);

}
