package com.azathoth.OLRResidency_Indigency.service;

import com.azathoth.OLRResidency_Indigency.DTO.ResidentDTO;
import com.azathoth.OLRResidency_Indigency.model.Resident;
import com.azathoth.OLRResidency_Indigency.repository.ResidentRepository;
import com.azathoth.OLRResidency_Indigency.repository.SantulanResidentsRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestDocumentService {

    private final SantulanResidentsRepository santulanResidentsRepository;
    private final ResidentRepository residentRepository;
    private final SendSms sms;

    public RequestDocumentService(SantulanResidentsRepository santulanResidentsRepository, ResidentRepository residentRepository, SendSms sms) {
        this.santulanResidentsRepository = santulanResidentsRepository;
        this.residentRepository = residentRepository;
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

    public Optional<Resident> processBarangayClearance(String request, @Valid ResidentDTO residentDTO) {
        // transfer dto to resident object
        Resident registeredResident = convertToEntity(residentDTO);

        /*
         * Send sms message here
         */
        sms.sendSmsMessage(request, residentDTO.getContactNumber());

        // save to db
        return Optional.of(residentRepository.save(registeredResident));
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
        resident.setCompleteAddress(residentDTO.getCompleteAddress());
        resident.setBirthDate(residentDTO.getBirthDate());
        return resident;
    }
}
