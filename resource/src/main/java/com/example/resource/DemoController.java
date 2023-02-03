package com.example.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class DemoController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/get")
    public ResponseEntity<String> getDemo(@RequestHeader MultiValueMap<String, String> headers) {
        log.info("called /get");

        headers.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, String.join("|", value)));
        });

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var principal = authentication.getName();
        log.info("principal: " + principal);

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8081/get", String.class);
        log.info("indirect response: " + response.getBody());

        return ResponseEntity.ok("GET demo");
    }

    @PostMapping(value = "/post")
    public ResponseEntity<String> postDemo(@RequestHeader MultiValueMap<String, String> headers) {
        log.info("called /post");

        headers.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, String.join("|", value)));
        });

        return ResponseEntity.ok("POST demo");
    }

    @GetMapping(value = "/indirect")
    public ResponseEntity<String> indirect(@AuthenticationPrincipal Jwt token) {
        log.info("calling resource two");

        ResponseEntity<String> response = restTemplate.getForEntity("http://resourcetwo:8081/get", String.class);

        return response;
    }
}
