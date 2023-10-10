package com.taahaagul.tgjwtsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/TG")
public class TGController {

    @GetMapping
    public ResponseEntity<String> tg() {
        return ResponseEntity.ok("TG");
    }
}
