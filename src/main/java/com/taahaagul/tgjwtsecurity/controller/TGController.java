package com.taahaagul.tgjwtsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/TG")
public class TGController {

    @GetMapping
    @PreAuthorize("hasAuthority('tg:read')")
    public String get() {
        return "GET:: TG controller";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('tg:create')")
    public String post() {
        return "POST:: TG controller";
    }

    @PutMapping
    @PreAuthorize("hasAuthority('tg:update')")
    public String put() {
        return "PUT:: TG controller";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('tg:delete')")
    public String delete() {
        return "DELETE:: admin controller";
    }
}
