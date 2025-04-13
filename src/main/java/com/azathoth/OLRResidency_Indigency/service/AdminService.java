package com.azathoth.OLRResidency_Indigency.service;

import com.azathoth.OLRResidency_Indigency.model.Resident;
import com.azathoth.OLRResidency_Indigency.model.UpdateResident;
import com.azathoth.OLRResidency_Indigency.repository.DocumentRequestRepository;
import com.azathoth.OLRResidency_Indigency.repository.ResidentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final ResidentRepository residentRepository;
    private final DocumentRequestRepository documentRequestRepository;

    public AdminService(ResidentRepository residentRepository, DocumentRequestRepository documentRequestRepository) {
        this.residentRepository = residentRepository;
        this.documentRequestRepository = documentRequestRepository;
    }

    public Optional<List<Resident>> getAllResidents() {
        try {
            return Optional.of(residentRepository.findAll());
        }
        catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    @Transactional
    public boolean deleteResident(Long id) {
        try {
            Optional<Resident> resident = residentRepository.findById(id);

            // if resident is present, then delete it and return true
            if(resident.isPresent()) {
                documentRequestRepository.deleteById(resident.get().getId());
                residentRepository.delete(resident.get());
                return true;
            }

            // if resident does not found, then return false
            return false;
        }
        catch (NullPointerException e) {
            return false;
        }
    }

    public boolean updateResident(Long id, UpdateResident updateResident) {
        try {
            Optional<Resident> resident = residentRepository.findById(id);

            // if resident is found, update it
            if(resident.isPresent()) {
                Resident residentToBeUpdate = resident.get();

                residentToBeUpdate.setFirstName(updateResident.getFirstName());
                residentToBeUpdate.setLastName(updateResident.getLastName());
                residentToBeUpdate.setMiddleName(updateResident.getMiddleName());
                residentToBeUpdate.setSuffix(updateResident.getSuffix());
                residentToBeUpdate.setAge(updateResident.getAge());
                residentToBeUpdate.setGender(updateResident.getGender());
                residentToBeUpdate.setStatus(updateResident.getStatus());
                residentToBeUpdate.setCompleteAddress(updateResident.getCompleteAddress());
                residentToBeUpdate.setBirthDate(updateResident.getBirthDate());

                // update resident
                residentRepository.save(residentToBeUpdate);
                return true;
            }

            // if resident does not found, then return false
            return false;
        }
        catch (NullPointerException e) {
            return false;
        }
    }

    public List<Resident> searchResident(String keyword) {
        return residentRepository.searchResidents(keyword);
    }
}
