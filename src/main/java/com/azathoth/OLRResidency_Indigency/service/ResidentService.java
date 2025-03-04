package com.azathoth.OLRResidency_Indigency.service;

import com.azathoth.OLRResidency_Indigency.DTO.ResidentDTO;
import com.azathoth.OLRResidency_Indigency.model.Resident;
import com.azathoth.OLRResidency_Indigency.repository.ResidentRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResidentService {

    private final ResidentRepository residentRepository;

    public ResidentService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    public Optional<Resident> register(@Valid ResidentDTO resident) {
        try {
            // transfer dto to resident object
            Resident registeredResident = convertToEntity(resident);

            // save to db
            return Optional.of(residentRepository.save(registeredResident));
        }
        catch (NullPointerException nullPointerException) {
            return Optional.empty();
        }
    }

    private Resident convertToEntity(ResidentDTO residentDTO) {
        Resident resident = new Resident();
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
