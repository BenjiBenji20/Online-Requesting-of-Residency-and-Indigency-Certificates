package com.azathoth.OLRResidency_Indigency.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/residents/public")
public class ResidentsController {

    @GetMapping("/greet")
    public String greeting() {
        return "Hello Malabon Residents!";
    }
}
