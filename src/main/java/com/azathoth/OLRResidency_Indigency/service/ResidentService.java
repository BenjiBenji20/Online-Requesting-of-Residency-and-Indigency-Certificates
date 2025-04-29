package com.azathoth.OLRResidency_Indigency.service;

import com.azathoth.OLRResidency_Indigency.DTO.ResidentDTO;
import com.azathoth.OLRResidency_Indigency.model.Resident;
import com.azathoth.OLRResidency_Indigency.repository.ResidentRepository;
import com.azathoth.OLRResidency_Indigency.repository.SantulanResidentsRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResidentService {

    private final ResidentRepository residentRepository;
    private final SantulanResidentsRepository santulanResidentsRepository;

    public ResidentService(ResidentRepository residentRepository, SantulanResidentsRepository santulanResidentsRepository) {
        this.residentRepository = residentRepository;
        this.santulanResidentsRepository = santulanResidentsRepository;
    }

    public Optional<Resident> register(@Valid ResidentDTO resident) {
        try {
            // first, find if the resident's combination of info exists in database
            // Note: this will be unique because of national id
            boolean isResidentExists = santulanResidentsRepository.findResidentByInfo(
                    resident.getNationalId(),
                    resident.getFirstName(),
                    resident.getLastName()
            );

            // if resident didn't exist return empty
            if(!isResidentExists) {
                return Optional.empty();
            }

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
