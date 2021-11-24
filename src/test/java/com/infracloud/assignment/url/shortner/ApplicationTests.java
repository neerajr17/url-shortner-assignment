package com.infracloud.assignment.url.shortner;

import com.infracloud.assignment.url.shortner.controller.UrlShortnerController;
import com.infracloud.assignment.url.shortner.service.UrlShortnerService;
import com.infracloud.assignment.url.shortner.service.impl.UrlShortnerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	UrlShortnerServiceImpl urlShortnerService;

	@Test
	public void testGetShortenedUrlAPI() {
		Mockito.doCallRealMethod().when(urlShortnerService).getUrlByKey(anyString());
		urlShortnerService.shortenUrl("www.test.com");

	}

}
