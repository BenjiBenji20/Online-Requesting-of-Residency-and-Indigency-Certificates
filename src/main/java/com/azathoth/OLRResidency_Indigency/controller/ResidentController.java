package com.azathoth.OLRResidency_Indigency.controller;

import com.azathoth.OLRResidency_Indigency.DTO.ResidentDTO;
import com.azathoth.OLRResidency_Indigency.model.Resident;
import com.azathoth.OLRResidency_Indigency.service.ResidentService;
import jakarta.validation.Valid;
import org.hibernate.exception.DataException;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/residents/public")
public class ResidentController {

    private final ResidentService residentService;

    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping("/greet")
    public String greeting() {
        return "Hello Malabon Residents!";
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerResident(@Valid @RequestBody ResidentDTO resident) {
        try {
            Optional<Resident> registeredResident = residentService.register(resident);

            if(registeredResident.isPresent()) {
                return ResponseEntity.ok(Map.of("message", "Registered successfully!"));
            }

            return ResponseEntity.badRequest().body(Map.of("error", "Registration failed"));
        }
        catch (DataException d) {
            Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
            return ResponseEntity.internalServerError().body(Map.of("error", "Server error"));
        }
    }
}
