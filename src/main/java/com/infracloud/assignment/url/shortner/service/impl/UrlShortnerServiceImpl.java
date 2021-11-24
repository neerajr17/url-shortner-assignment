package com.infracloud.assignment.url.shortner.service.impl;

import com.google.common.hash.Hashing;
import com.infracloud.assignment.url.shortner.model.UrlResponse;
import com.infracloud.assignment.url.shortner.service.UrlShortnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import java.nio.charset.Charset;
import java.time.LocalDateTime;

@Service
@Slf4j
public class UrlShortnerServiceImpl implements UrlShortnerService {

    @Autowired
    private RedisTemplate<String, UrlResponse> redisTemplate;

    @Override
    public String getUrlByKey(@NotBlank String hash) {
        UrlResponse url = redisTemplate.opsForValue().get(hash);
        return url.getUrl();
    }

    @Override
    public UrlResponse shortenUrl(String url) {

        try {
            String hash = Hashing.murmur3_32().hashString(url, Charset.defaultCharset()).toString();
            String originalUrl = getUrlByKey(hash);
            if (originalUrl == null || StringUtils.isEmpty(originalUrl)) {
                UrlResponse shortUrl = UrlResponse.builder().key(hash).createdDateTime(LocalDateTime.now()).url(url).build();
                redisTemplate.opsForValue().set(hash, shortUrl);
            }
            return UrlResponse.builder().key(hash).url(url).createdDateTime(LocalDateTime.now()).url(url).build();
        } catch (Exception e) {
            log.error("Exception occured while executing shorten URL Service", e.getMessage());
            return UrlResponse.builder().build();
        }
    }

}

