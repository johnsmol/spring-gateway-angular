package com.example.resourcetwo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DemoController {

    @GetMapping(value = "/get")
    public ResponseEntity<String> getDemo(@RequestHeader MultiValueMap<String, String> headers) {
        log.info("called /get of resource two");

        headers.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, String.join("|", value)));
        });

        return ResponseEntity.ok("GET demo from resource two");
    }

    @PostMapping(value = "/post")
    public ResponseEntity<String> postDemo(@RequestHeader MultiValueMap<String, String> headers) {
        log.info("called /post of resource two");

        headers.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, String.join("|", value)));
        });

        return ResponseEntity.ok("POST demo from resource two");
    }
}
