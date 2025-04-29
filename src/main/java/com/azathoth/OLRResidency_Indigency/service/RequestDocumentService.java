package com.azathoth.OLRResidency_Indigency.service;

import com.azathoth.OLRResidency_Indigency.DTO.ResidentDTO;
import com.azathoth.OLRResidency_Indigency.model.DocumentRequest;
import com.azathoth.OLRResidency_Indigency.model.DocumentType;
import com.azathoth.OLRResidency_Indigency.model.Resident;
import com.azathoth.OLRResidency_Indigency.repository.DocumentRequestRepository;
import com.azathoth.OLRResidency_Indigency.repository.ResidentRepository;
import com.azathoth.OLRResidency_Indigency.repository.SantulanResidentsRepository;
import com.azathoth.OLRResidency_Indigency.util.DocumentStatus;
import jakarta.validation.Valid;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class RequestDocumentService {

    private final SantulanResidentsRepository santulanResidentsRepository;
    private final ResidentRepository residentRepository;
    private final DocumentRequestRepository documentRequestRepository;
    private final SendSms sms;

    public RequestDocumentService(SantulanResidentsRepository santulanResidentsRepository, ResidentRepository residentRepository, DocumentRequestRepository documentRequestRepository, SendSms sms) {
        this.santulanResidentsRepository = santulanResidentsRepository;
        this.residentRepository = residentRepository;
        this.documentRequestRepository = documentRequestRepository;
        this.sms = sms;
    }

    // find resident if available in database using its info
    public boolean findResident(long nationalId, String firstName, String lastName) {
        // first, find if the resident's combination of info exists in database
        // Note: this will be unique because of national id
        return santulanResidentsRepository.findResidentByInfo(
                nationalId, firstName, lastName
        );
    }

    public Optional<DocumentRequest> processDocument(DocumentType request, @Valid ResidentDTO residentDTO) {
        if (isRequestLimitReached(request, LocalDate.now())) {
            return Optional.empty(); // limit reached
        }

        // transfer dto to resident object
        Resident registeredResident = convertToEntity(residentDTO);

        // save to repos
        residentRepository.save(registeredResident);

        // call method to handle document and directly save to db
        DocumentRequest documentRequest = handleDocumentRequest(registeredResident, request);

        // sned sms using twilio
        // sms.sendSmsMessage(request.toString(), documentRequest.getDueDate(), residentDTO.getContactNumber());

        return Optional.of(documentRequest);
    }

    private static final int MAX_DAILY_REQUESTS = 50;

    private boolean isRequestLimitReached(DocumentType type, LocalDate date) {
        long requestCount = documentRequestRepository.countByTypeAndDate(type, date);
        return requestCount >= MAX_DAILY_REQUESTS;
    }


    @Scheduled(cron = "0 0 0 * * ?") // Runs daily at midnight
    public void updateRequestStatuses() {
        LocalDate today = LocalDate.now();
        List<DocumentRequest> dueRequests = documentRequestRepository
                .findByStatusAndDate(DocumentStatus.PENDING, today);

        dueRequests.forEach(request -> request.setStatus(DocumentStatus.FOR_PICKUP));
        documentRequestRepository.saveAll(dueRequests);
    }

    // handles document request its days according to its type
    private DocumentRequest handleDocumentRequest(Resident resident, DocumentType documentType) {
        DocumentRequest request = new DocumentRequest();
        request.setRequestDate(LocalDate.now());
        request.setDocumentType(documentType);
        request.setResident(resident);

        LocalDateTime now = LocalDateTime.now();
        LocalTime currentTime = LocalTime.now();

        LocalTime openingTime = LocalTime.of(9, 0); // 9:00 AM
        LocalTime closingTime = LocalTime.of(21, 0); // 9:00 PM

        LocalDateTime dueDateTime;

        if(currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime)) {
            // if current time is within business time. Between 9:00 AM to 9:00 PM
            dueDateTime = now.plusHours(documentType.getProcessingHours()); // add the document processing hours
        }
        else {
            // request before 9:00 AM and after 9:00 PM
            LocalDateTime nextDayPickUp = LocalDateTime.of(LocalDate.now().plusDays(1), openingTime);
            dueDateTime = nextDayPickUp.plusHours(documentType.getProcessingHours()); // add the document processing hours
        }

        request.setDueDate(dueDateTime);

        return documentRequestRepository.save(request);
    }

    private Resident convertToEntity(ResidentDTO residentDTO) {
        Resident resident = new Resident();
        resident.setNationalId(residentDTO.getNationalId());
        resident.setContactNumber(residentDTO.getContactNumber());
        resident.setPurpose(residentDTO.getPurpose());
        resident.setFirstName(residentDTO.getFirstName());
        resident.setLastName(residentDTO.getLastName());
        resident.setMiddleName(residentDTO.getMiddleName());
        resident.setSuffix(residentDTO.getSuffix());
        resident.setAge(residentDTO.getAge());
        resident.setGender(residentDTO.getGender());
        resident.setStatus(residentDTO.getStatus());
        resident.setHouseNumber(residentDTO.getHouseNumber());
        resident.setStreet(residentDTO.getStreet());
        resident.setSubdivision(residentDTO.getSubdivision());
        resident.setBarangay(residentDTO.getBarangay());
        resident.setCityMunicipality(residentDTO.getCityMunicipality());
        resident.setProvince(residentDTO.getProvince());
        resident.setPostalCode(residentDTO.getPostalCode());
        resident.setRegion(residentDTO.getRegion());
        resident.setBirthDate(residentDTO.getBirthDate());
        return resident;
    }
}
