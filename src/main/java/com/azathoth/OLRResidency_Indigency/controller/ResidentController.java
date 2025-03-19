package com.azathoth.OLRResidency_Indigency.controller;

import com.azathoth.OLRResidency_Indigency.DTO.ResidentDTO;
import com.azathoth.OLRResidency_Indigency.model.Resident;
import com.azathoth.OLRResidency_Indigency.service.PdfGeneratorService;
import com.azathoth.OLRResidency_Indigency.service.ResidentService;
import jakarta.validation.Valid;
import org.hibernate.exception.DataException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/residents/public")
public class ResidentController {

    private final ResidentService residentService;
    private final PdfGeneratorService pdfGeneratorService;

    public ResidentController(ResidentService residentService, PdfGeneratorService pdfGeneratorService) {
        this.residentService = residentService;
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("/greet")
    public String greeting() {
        return "Hello Malabon Residents!";
    }

    @PostMapping("/register-and-request/{requestType}")
    public ResponseEntity<?> registerResident(@PathVariable String requestType,
                                              @Valid @RequestBody ResidentDTO resident) {
        try {
            Optional<Resident> registeredResident = residentService.register(resident);

            if(registeredResident.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            // generate pdf based on request type
            byte[] generatePDF;
            String fileName;

            // generate pdf
            switch (requestType.toLowerCase()) {
                case "indigency" :
                    generatePDF = pdfGeneratorService.generateIndigencyPDF(registeredResident.get());
                    fileName = "indigency-" + registeredResident.get().getFirstName() + " " +
                            registeredResident.get().getLastName() + "-request-info.pdf";
                    break;

                case "residency" :
                    generatePDF = pdfGeneratorService.generateResidencyPDF(registeredResident.get());
                    fileName = "residency-" + registeredResident.get().getFirstName() + " " +
                            registeredResident.get().getLastName() + "-request-info.pdf";
                    break;

                default:
                    return ResponseEntity.badRequest().body(Map.of("error", "Failed request"));
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF); // allow headers to send pdf

            // create file name
            headers.setContentDispositionFormData("attachment", fileName);

            // return pdf as response
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(generatePDF);
        }
        catch (DataException d) {
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to register"));
        }
        catch (IOException io) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Failed to generate pdf"));
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Server cannot response"));
        }
    }
}
