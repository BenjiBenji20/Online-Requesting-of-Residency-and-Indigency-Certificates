package com.azathoth.OLRResidency_Indigency.controller;

import com.azathoth.OLRResidency_Indigency.DTO.SantulanResidentsDTO;
import com.azathoth.OLRResidency_Indigency.model.SantulanResidents;
import com.azathoth.OLRResidency_Indigency.service.SantulanResidentsService;
import jakarta.validation.Valid;
import org.hibernate.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/santulan/residents/public")
public class SantulanResidentController {
    private static final Logger logger = LoggerFactory.getLogger(SantulanResidentController.class);

    private final SantulanResidentsService santulanResidentsService;

    public SantulanResidentController(SantulanResidentsService santulanResidentsService) {
        this.santulanResidentsService = santulanResidentsService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerSantulanResident(@Valid @RequestBody SantulanResidentsDTO santulanResidentsDTO) {
        try {
            Optional<SantulanResidents> registerResident = santulanResidentsService.registerSantulanResident(santulanResidentsDTO);

            return registerResident.isEmpty() ?
                    ResponseEntity.badRequest().body(
                            Map.of("error", "Resident already exists by unique national id: " + santulanResidentsDTO.getNationalId())) :
                    ResponseEntity.ok().body(Map.of("message", "Resident registered successfully"));
        }
        catch (DataException d) {
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to register"));
        }
        catch (Exception e) {
            logger.error("Server error", e.getCause());
            return ResponseEntity.internalServerError().body(Map.of("error", "Internal server error"));
        }
    }
}
