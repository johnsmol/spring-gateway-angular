package com.example.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DemoController {

    @GetMapping(value = "/get")
    public ResponseEntity<String> getDemo() {
        log.info("called /get");
        return ResponseEntity.ok("GET demo");
    }

    @PostMapping(value = "/post")
    public ResponseEntity<String> postDemo() {
        log.info("called /post");
        return ResponseEntity.ok("POST demo");
    }
}
