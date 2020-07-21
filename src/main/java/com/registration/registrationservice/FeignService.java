package com.registration.registrationservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import feign.Headers;
import feign.Response;

@FeignClient(name = "mailService", url = "${spring.feign.client.url.TestUrl}/mail/sendMail")
public interface FeignService {
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	@Headers("Content-Type: application/json")
	public Response getEmails(@RequestBody String str);
}
