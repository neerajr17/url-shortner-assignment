package com.infracloud.assignment.url.shortner.controller;

import com.infracloud.assignment.url.shortner.model.UrlResponse;
import com.infracloud.assignment.url.shortner.service.UrlShortnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/urlShortener")
public class UrlShortnerController {

    @Autowired
    UrlShortnerService urlShortnerService;

    @RequestMapping(value = "/{url}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity shortenUrl(@PathVariable String url) {
        UrlResponse shortUrl = urlShortnerService.shortenUrl(url);
        if (shortUrl != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", shortUrl.getUrl());
            return new ResponseEntity<String>(headers, HttpStatus.FOUND);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/{hash}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getUrl(@PathVariable String hash) {
        String url = urlShortnerService.getUrlByKey(hash);
        return ResponseEntity.ok(url);
    }

}
