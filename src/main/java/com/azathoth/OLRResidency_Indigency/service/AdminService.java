package com.azathoth.OLRResidency_Indigency.service;

import com.azathoth.OLRResidency_Indigency.DTO.ResidentDTO;
import com.azathoth.OLRResidency_Indigency.model.Resident;
import com.azathoth.OLRResidency_Indigency.model.UpdateResident;
import com.azathoth.OLRResidency_Indigency.repository.DocumentRequestRepository;
import com.azathoth.OLRResidency_Indigency.repository.ResidentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {
    private final ResidentRepository residentRepository;
    private final DocumentRequestRepository documentRequestRepository;

    public AdminService(ResidentRepository residentRepository, DocumentRequestRepository documentRequestRepository) {
        this.residentRepository = residentRepository;
        this.documentRequestRepository = documentRequestRepository;
    }

    public Optional<List<ResidentDTO>> getAllResidents() {
        try {
            List<Resident> residents = residentRepository.findAll();

            // Convert each Resident object to ResidentDTO
            List<ResidentDTO> residentDTOs = residents.stream()
                    .map(this::convertToResidentDTO)
                    .collect(Collectors.toList());

            return Optional.of(residentDTOs);
        } catch (NullPointerException e) {
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
                residentToBeUpdate.setHouseNumber(updateResident.getHouseNumber());
                residentToBeUpdate.setStreet(updateResident.getStreet());
                residentToBeUpdate.setSubdivision(updateResident.getSubdivision());
                residentToBeUpdate.setBarangay(updateResident.getBarangay());
                residentToBeUpdate.setCityMunicipality(updateResident.getCityMunicipality());
                residentToBeUpdate.setProvince(updateResident.getProvince());
                residentToBeUpdate.setPostalCode(updateResident.getPostalCode());
                residentToBeUpdate.setRegion(updateResident.getRegion());
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

    public List<ResidentDTO> searchResident(String keyword) {
        List<Resident> residents = residentRepository.searchResidents(keyword);

        return residents.stream()
                .map(this::convertToResidentDTO)
                .toList();
    }

    private ResidentDTO convertToResidentDTO(Resident resident) {
        ResidentDTO dto = new ResidentDTO();

        dto.setId(resident.getId());
        dto.setNationalId(resident.getNationalId());
        dto.setFirstName(resident.getFirstName());
        dto.setLastName(resident.getLastName());
        dto.setMiddleName(resident.getMiddleName());
        dto.setSuffix(resident.getSuffix());
        dto.setAge(resident.getAge());
        dto.setGender(resident.getGender());
        dto.setStatus(resident.getStatus());

        // Set each address part individually
        dto.setHouseNumber(resident.getHouseNumber());
        dto.setStreet(resident.getStreet());
        dto.setSubdivision(resident.getSubdivision());
        dto.setBarangay(resident.getBarangay());
        dto.setCityMunicipality(resident.getCityMunicipality());
        dto.setProvince(resident.getProvince());
        dto.setPostalCode(resident.getPostalCode());
        dto.setRegion(resident.getRegion());

        dto.setBirthDate(resident.getBirthDate());
        dto.setPurpose(resident.getPurpose());
        dto.setContactNumber(resident.getContactNumber());

        return dto;
    }
}
