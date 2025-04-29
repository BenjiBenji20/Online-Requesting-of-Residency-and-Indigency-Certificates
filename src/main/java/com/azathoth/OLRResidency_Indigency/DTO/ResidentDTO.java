package com.azathoth.OLRResidency_Indigency.DTO;

import com.azathoth.OLRResidency_Indigency.util.Purpose;
import com.azathoth.OLRResidency_Indigency.util.Status;
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

    @NotBlank(message = "House number cannot be empty")
    @Pattern(regexp = "^[\\p{L}\\p{N}\\p{Z},.\\-#/]{1,}$", message = "House number contains invalid characters")
    @Size(max = 50)
    private String houseNumber;

    @NotBlank(message = "Street cannot be empty")
    @Pattern(regexp = "^[\\p{L}\\p{N}\\p{Z},.\\-]{1,}$", message = "Street contains invalid characters")
    @Size(max = 100)
    private String street;

    @Pattern(regexp = "^[\\p{L}\\p{N}\\p{Z},.\\-]{1,}$", message = "Subdivision contains invalid characters")
    @Size(max = 100)
    private String subdivision;

    @NotBlank(message = "Barangay cannot be empty")
    @Pattern(regexp = "^[\\p{L}\\p{Z},.\\-]{1,}$", message = "Barangay contains invalid characters")
    @Size(max = 100)
    private String barangay;

    @NotBlank(message = "City/Municipality cannot be empty")
    @Pattern(regexp = "^[\\p{L}\\p{Z},.\\-]{1,}$", message = "City/Municipality contains invalid characters")
    @Size(max = 100)
    private String cityMunicipality;

    @NotBlank(message = "Province cannot be empty")
    @Pattern(regexp = "^[\\p{L}\\p{Z},.\\-]{1,}$", message = "Province contains invalid characters")
    @Size(max = 100)
    private String province;

    @NotBlank(message = "Postal code cannot be empty")
    @Pattern(regexp = "^\\d{4}$", message = "Postal code must be exactly 4 digits")
    private String postalCode;

    @NotBlank(message = "Region cannot be empty")
    @Pattern(regexp = "^[\\p{L}\\p{Z},.\\-]{1,}$", message = "Region contains invalid characters")
    @Size(max = 100)
    private String region;

    @NotNull(message = "Birth date cannot be null")
    @Past(message = "Date must be from the past")
    private LocalDate birthDate;

    @NotNull(message = "Purpose cannot be empty")
    private Purpose purpose;

    @NotBlank(message = "Please provide your contact number")
    @Size(max = 11)
    @Pattern(regexp = "^[0-9]+$", message = "Contact number only allow integer digits")
    private String contactNumber;

    public ResidentDTO(long id, long nationalId, String firstName, String lastName, String middleName,
                       String suffix, Integer age, String gender, Status status, String houseNumber,
                       String street, String subdivision, String barangay, String cityMunicipality,
                       String province, String postalCode, String region, LocalDate birthDate, Purpose purpose,
                       String contactNumber) {
        this.id = id;
        this.nationalId = nationalId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.suffix = suffix;
        this.age = age;
        this.gender = gender;
        this.status = status;
        this.houseNumber = houseNumber;
        this.street = street;
        this.subdivision = subdivision;
        this.barangay = barangay;
        this.cityMunicipality = cityMunicipality;
        this.province = province;
        this.postalCode = postalCode;
        this.region = region;
        this.birthDate = birthDate;
        this.purpose = purpose;
        this.contactNumber = contactNumber;
    }

    public ResidentDTO() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNationalId() {
        return nationalId;
    }

    public void setNationalId(long nationalId) {
        this.nationalId = nationalId;
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

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getCityMunicipality() {
        return cityMunicipality;
    }

    public void setCityMunicipality(String cityMunicipality) {
        this.cityMunicipality = cityMunicipality;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
