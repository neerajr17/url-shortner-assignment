package com.infracloud.assignment.url.shortner.controller;

import com.infracloud.assignment.url.shortner.service.UrlShortnerService;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UrlShortnerController {

    @Autowired
    UrlShortnerService urlShortnerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void redirect(@PathVariable String id, HttpServletResponse resp) throws Exception {
        final String url = urlShortnerService.getUrlByKey(id);
        if (url != null)
            resp.sendRedirect(url);
        else
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> save(HttpServletRequest req) {
        final String queryParams = (req.getQueryString() != null) ? req.getQueryString() : "";
        final String url = (req.getRequestURI() + queryParams).substring(1);
        final UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
        if (urlValidator.isValid(url)) {
            String id = urlShortnerService.shortenUrl(url);
            return new ResponseEntity<>("http://localhost:8080/" + id, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
