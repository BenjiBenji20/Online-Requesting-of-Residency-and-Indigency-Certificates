package com.azathoth.OLRResidency_Indigency.model;

import com.azathoth.OLRResidency_Indigency.util.Purpose;
import com.azathoth.OLRResidency_Indigency.util.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "malabon_resident")
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "national_id_number", nullable = false, updatable = false, length = 12)
    private long nationalId;

    // auto generated date when new object enters the db
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date dateAt;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "middle_name", length = 100) // Optional field
    private String middleName;

    @Column(length = 2) // Suffix (e.g., Jr., Sr.)
    private String suffix;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, length = 10)
    private String gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    // address
    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "subdivision")
    private String subdivision;

    @Column(name = "barangay", nullable = false)
    private String barangay;

    @Column(name = "city_municipality", nullable = false)
    private String cityMunicipality;

    @Column(name = "province", nullable = false)
    private String province;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Purpose purpose;

    @Column(name = "contact_no", length = 11, nullable = false)
    private String contactNumber;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
    private List<DocumentRequest> documentRequests;

    public Resident(long id, long nationalId, Date dateAt, String firstName, String lastName, String middleName,
                    String suffix, Integer age, String gender, Status status, String houseNumber, String street,
                    String subdivision, String barangay, String cityMunicipality, String province, String postalCode,
                    String region, LocalDate birthDate, Purpose purpose, String contactNumber,
                    List<DocumentRequest> documentRequests) {
        this.id = id;
        this.nationalId = nationalId;
        this.dateAt = dateAt;
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
        this.documentRequests = documentRequests;
    }

    public Resident() {}

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

    public Date getDateAt() {
        return dateAt;
    }

    public void setDateAt(Date dateAt) {
        this.dateAt = dateAt;
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

    public List<DocumentRequest> getDocumentRequests() {
        return documentRequests;
    }

    public void setDocumentRequests(List<DocumentRequest> documentRequests) {
        this.documentRequests = documentRequests;
    }
}
