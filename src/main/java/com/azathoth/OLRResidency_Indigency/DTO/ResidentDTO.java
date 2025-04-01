package com.azathoth.OLRResidency_Indigency.DTO;

import com.azathoth.OLRResidency_Indigency.model.Resident;
import com.azathoth.OLRResidency_Indigency.util.Purpose;
import com.azathoth.OLRResidency_Indigency.util.Status;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

/**
 * regex pattern ^\p{L}+[\p{L}\p{Z}\p{P}]{0,}
 * allows : Letters (A-Z, a-z, including Unicode characters like ñ, é, ü)
 * and white spaces at between characters
 */
public class ResidentDTO {
    private long id;

    @NotNull(message = "National id number cannot be empty")
    @Digits(integer = 12, fraction = 0, message = "National ID must be exactly 12 digits")
    private long nationalId;

    @NotBlank(message = "First name cannot be empty")
    @Size(min = 2, max = 100, message = "First name must be between 2-100 characters")
    @Pattern(regexp = "^\\p{L}+[\\p{L}\\p{Z}]{0,}", message = "First name cannot contain special characters or spaces")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 2, max = 100, message = "Last name must be between 2-100 characters")
    @Pattern(regexp = "^\\p{L}+[\\p{L}\\p{Z}]{0,}", message = "Last name cannot contain special characters or spaces")
    private String lastName;

    @Size(min = 2, max = 100, message = "Middle name must be between 2-100 characters")
    @Pattern(regexp = "^\\p{L}+[\\p{L}\\p{Z}]{0,}", message = "Middle name cannot contain special characters or spaces")
    private String middleName;

    @Size(min = 1, max = 2, message = "Suffix must be 1-2 characters")
    @Pattern(regexp = "^\\p{L}+[\\p{L}\\p{Z}]{0,}", message = "Suffix cannot contain special characters or spaces")
    private String suffix;

    @NotNull(message = "Age cannot be null")
    @Min(value = 0, message = "Age must be a positive number")
    private Integer age;

    @NotBlank(message = "Gender cannot be empty")
    @Pattern(regexp = "^\\p{L}+[\\p{L}\\p{Z}]{0,}", message = "Gender cannot contain special characters or spaces")
    @Size(min = 1, max = 10, message = "Gender must be 1-10 characters")
    private String gender;

    @NotNull(message = "Status cannot be null")
    private Status status;

    @NotBlank(message = "Address cannot be empty")
    @Pattern(regexp = "^[\\p{L}\\p{N}][\\p{L}\\p{N}\\p{Z},.]{0,}$", message = "Address cannot contain special characters")
    @Size(max = 255)
    private String completeAddress;

    @NotNull(message = "Birth date cannot be null")
    @Past(message = "Date must be from the past")
    private LocalDate birthDate;

    @NotNull(message = "Purpose cannot be empty")
    private Purpose purpose;

    @NotBlank(message = "Please provide your contact number")
    @Size(max = 11)
    @Pattern(regexp = "^[0-9]+$", message = "Contact number only allow integer digits")
    private String contactNumber;

    public ResidentDTO(long id, long nationalId, String firstName, String lastName, String middleName, String suffix,
                       Integer age, String gender, Status status, String completeAddress, LocalDate birthDate,
                       Purpose purpose, String contactNumber) {
        this.id = id;
        this.purpose = purpose;
        this.nationalId = nationalId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.suffix = suffix;
        this.contactNumber = contactNumber;
        this.age = age;
        this.gender = gender;
        this.status = status;
        this.completeAddress = completeAddress;
        this.birthDate = birthDate;
    }

    public ResidentDTO() {
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public long getNationalId() {
        return nationalId;
    }

    public void setNationalId(long nationalId) {
        this.nationalId = nationalId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCompleteAddress() {
        return completeAddress;
    }

    public void setCompleteAddress(String completeAddress) {
        this.completeAddress = completeAddress;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public ResidentDTO convertToResidentDTO(Resident resident) {
        ResidentDTO dto = new ResidentDTO();

        dto.setId(resident.getId());
        dto.setFirstName(resident.getFirstName());
        dto.setLastName(resident.getLastName());
        dto.setMiddleName(resident.getMiddleName());
        dto.setSuffix(resident.getSuffix());
        dto.setAge(resident.getAge());
        dto.setGender(resident.getGender());
        dto.setStatus(resident.getStatus());
        dto.setCompleteAddress(resident.getCompleteAddress());
        dto.setBirthDate(resident.getBirthDate());

        return dto;
    }
}
