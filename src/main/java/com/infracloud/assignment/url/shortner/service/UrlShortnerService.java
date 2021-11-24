package com.infracloud.assignment.url.shortner.service;

import com.infracloud.assignment.url.shortner.model.UrlResponse;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
public interface UrlShortnerService {

    public UrlResponse shortenUrl(@NotBlank String url);

    public String getUrlByKey(@NotBlank String key);

}
