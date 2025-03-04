package com.azathoth.OLRResidency_Indigency.model;

import com.azathoth.OLRResidency_Indigency.util.Status;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UpdateResident {
    private long id;

    @NotBlank(message = "First name cannot be empty")
    @Size(min = 2, max = 100, message = "First name must be between 2-100 characters")
    @Pattern(regexp = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}", message = "First name cannot contain special characters or spaces")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 2, max = 100, message = "Last name must be between 2-100 characters")
    @Pattern(regexp = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}", message = "Last name cannot contain special characters or spaces")
    private String lastName;

    @Size(min = 2, max = 100, message = "Middle name must be between 2-100 characters")
    @Pattern(regexp = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}", message = "Middle name cannot contain special characters or spaces")
    private String middleName;

    @Size(min = 1, max = 2, message = "Suffix must be 1-2 characters")
    @Pattern(regexp = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}", message = "Suffix cannot contain special characters or spaces")
    private String suffix;

    @NotNull(message = "Age cannot be null")
    @Min(value = 0, message = "Age must be a positive number")
    private Integer age;

    @NotBlank(message = "Gender cannot be empty")
    @Size(min = 1, max = 10, message = "Gender must be 1-10 characters")
    private String gender;

    @NotNull(message = "Status cannot be null")
    private Status status;

    @NotBlank(message = "Address cannot be empty")
    private String completeAddress;

    @NotNull(message = "Birth date cannot be null")
    @Past(message = "Date must be from the past")
    private LocalDate birthDate;

    public UpdateResident(long id, String firstName, String lastName, String middleName, String suffix, Integer age, String gender, Status status, String completeAddress, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.suffix = suffix;
        this.age = age;
        this.gender = gender;
        this.status = status;
        this.completeAddress = completeAddress;
        this.birthDate = birthDate;
    }

    public UpdateResident() {
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
}
