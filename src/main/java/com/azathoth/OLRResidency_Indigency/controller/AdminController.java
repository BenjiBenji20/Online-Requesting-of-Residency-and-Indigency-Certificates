package com.azathoth.OLRResidency_Indigency.controller;

import com.azathoth.OLRResidency_Indigency.DTO.ResidentDTO;
import com.azathoth.OLRResidency_Indigency.model.Resident;
import com.azathoth.OLRResidency_Indigency.model.UpdateResident;
import com.azathoth.OLRResidency_Indigency.service.AdminService;
import org.hibernate.exception.DataException;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/admin/private")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/greet")
    public String greet() {
        return "Hello admin";
    }

    @GetMapping("/get-all-residents")
    public ResponseEntity<?> getAllResidents() {
        try {
            Optional<List<ResidentDTO>> allResidents = adminService.getAllResidents();

            if(allResidents.isPresent()) {
                return ResponseEntity.ok(allResidents);
            }

            return ResponseEntity.notFound().build();
        }
        catch (DataException d) {
            Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
            return ResponseEntity.internalServerError().body(Map.of("error", "Server error"));
        }
    }

    @DeleteMapping("/delete-resident/{id}")
    public ResponseEntity<?> deleteResident(@PathVariable Long id) {
        try {
            adminService.deleteResident(id);
            return ResponseEntity.ok().body(Map.of(
                    "success", true,
                    "message", "Resident deleted successfully"
            ));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "success", false,
                    "error", "Cannot delete resident",
                    "message", "This resident has associated documents. Delete documents first."
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "success", false,
                    "error", "Server error",
                    "message", e.getMessage()
            ));
        }
    }

    @PutMapping("/update-resident/{id}")
    public ResponseEntity<?> updateResident(@PathVariable Long id,
                                            @RequestBody UpdateResident updateResident) {
        try {
            boolean isResidentUpdated = adminService.updateResident(id, updateResident);

            if(isResidentUpdated) {
                return ResponseEntity.ok().body(Map.of("message", "Resident successfully updated"));
            }
            return ResponseEntity.notFound().build();
        }
        catch (DataException d) {
            d.printStackTrace();
            Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
            return ResponseEntity.internalServerError().body(Map.of("error", "Server error"));
        }
    }

    @GetMapping("/search-resident")
    public ResponseEntity<?> searchResident(
            @RequestParam(required = false) String keyword
    ) {
        try {
            List<ResidentDTO> residents = adminService.searchResident(keyword);

            if(residents.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(residents);
        }
        catch (DataException d) {
            Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
            return ResponseEntity.internalServerError().body(Map.of("error", "Server error"));
        }
    }
}
