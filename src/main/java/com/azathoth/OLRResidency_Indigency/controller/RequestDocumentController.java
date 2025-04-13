package com.azathoth.OLRResidency_Indigency.controller;

import com.azathoth.OLRResidency_Indigency.DTO.ResidentDTO;
import com.azathoth.OLRResidency_Indigency.model.DocumentRequest;
import com.azathoth.OLRResidency_Indigency.model.DocumentType;
import com.azathoth.OLRResidency_Indigency.service.RequestDocumentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/documents/public")
public class RequestDocumentController {

    private static Logger logger = LoggerFactory.getLogger(RequestDocumentController.class);
    private final RequestDocumentService requestDocumentService;

    public RequestDocumentController(RequestDocumentService requestDocumentService) {
        this.requestDocumentService = requestDocumentService;
    }

    @PostMapping("/request/{documentType}")
    public ResponseEntity<?> requestDocument(@PathVariable DocumentType documentType, @Valid @RequestBody ResidentDTO residentDTO) {
        try {
            boolean isResidentExists = requestDocumentService.findResident(
                    residentDTO.getNationalId(), residentDTO.getFirstName(), residentDTO.getLastName()
            );

            if(!isResidentExists) {
                return ResponseEntity.badRequest().body(Map.of("error", "Resident didn't exists by national id: "
                        + residentDTO.getNationalId() + " Please register first in barangay."));
            }

            String request = "";
            Optional<DocumentRequest> documentRequest;

            switch (documentType.toString().toLowerCase()) {
                case "barangay_clearance" :
                    request = "barangay clearance";
                    documentRequest = requestDocumentService.processBarangayClearance(documentType, residentDTO);
                    break;

                default :
                    return ResponseEntity.badRequest().body(Map.of("error", "Bad request"));
            }

            return documentRequest.isEmpty() ?
                    ResponseEntity.badRequest().body(Map.of("error", "Resident didn't exists by national id: "
                            + residentDTO.getNationalId() + " Please register first in barangay.")) :
                    ResponseEntity.ok().body(Map.of(
                            "message", "Request submitted successfully",
                            "request", request,
                            "dueDate", documentRequest.get().getDueDate()
                    ));
        }
        catch (HttpClientErrorException e) {
            logger.error("SMS sending failed: HTTP error - Status: {}, Response: {}",
                    e.getStatusCode(), e.getResponseBodyAsString(), e);
            return ResponseEntity.internalServerError().body(Map.of("error", "SMS sending failed"));
        }
        catch (Exception e) {
            logger.error("Error to process request for resident with national id: {}, {}\nError: {}",
                    residentDTO.getNationalId(), residentDTO.getFirstName(), residentDTO.getLastName(), e.fillInStackTrace());

            return ResponseEntity.internalServerError().body(Map.of("error", "Internal server error"));
        }

    }
}
