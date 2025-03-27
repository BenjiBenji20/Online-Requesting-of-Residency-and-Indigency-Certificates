package com.azathoth.OLRResidency_Indigency.service;

import com.azathoth.OLRResidency_Indigency.DTO.SantulanResidentsDTO;
import com.azathoth.OLRResidency_Indigency.model.SantulanResidents;
import com.azathoth.OLRResidency_Indigency.repository.SantulanResidentsRepository;
import jakarta.validation.Valid;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SantulanResidentsService {
    private final SantulanResidentsRepository santulanResidentsRepository;

    public SantulanResidentsService(SantulanResidentsRepository santulanResidentsRepository) {
        this.santulanResidentsRepository = santulanResidentsRepository;
    }

    public Optional<SantulanResidents> registerSantulanResident(@Valid SantulanResidentsDTO santulanResidentsDTO) {
        try {
            // first find if the resident's national id was existing, if yes then the resident already registered
            boolean isResidentExists = santulanResidentsRepository.findResidentByNationalId(santulanResidentsDTO.getNationalId());

            // if resident already exists
            if(isResidentExists) {
                return Optional.empty();
            }

            // convert the santulan resident dto into entity
            SantulanResidents resident = convertToSantulanResidentEntity(santulanResidentsDTO);

            return Optional.of(santulanResidentsRepository.save(resident));
        }
        catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    private SantulanResidents convertToSantulanResidentEntity(@Valid SantulanResidentsDTO santulanResidentsDTO) {
        SantulanResidents resident = new SantulanResidents();

        resident.setNationalId(santulanResidentsDTO.getNationalId());
        resident.setFirstName(santulanResidentsDTO.getFirstName());
        resident.setLastName(santulanResidentsDTO.getLastName());
        resident.setMiddleName(santulanResidentsDTO.getMiddleName());
        resident.setSuffix(santulanResidentsDTO.getSuffix());
        resident.setAge(santulanResidentsDTO.getAge());
        resident.setGender(santulanResidentsDTO.getGender());
        resident.setStatus(santulanResidentsDTO.getStatus());
        resident.setCompleteAddress(santulanResidentsDTO.getCompleteAddress());
        resident.setBirthDate(santulanResidentsDTO.getBirthDate());

        return resident;
    }
}
