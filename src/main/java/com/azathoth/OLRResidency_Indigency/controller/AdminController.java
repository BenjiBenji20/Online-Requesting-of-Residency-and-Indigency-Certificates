package com.azathoth.OLRResidency_Indigency.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/private")
public class AdminController {

    @GetMapping("/greet")
    public String greet() {
        return "Hello admin";
    }

    @GetMapping("/error-login")
    public String failureUrl() {
        return "Login failed";
    }

}
