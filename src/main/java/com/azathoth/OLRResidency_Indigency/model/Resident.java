package com.azathoth.OLRResidency_Indigency.model;

import com.azathoth.OLRResidency_Indigency.util.Purpose;
import com.azathoth.OLRResidency_Indigency.util.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "malabon_resident", uniqueConstraints = {
        @UniqueConstraint(columnNames = "national_id_number")
})
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

    @Column(name = "first_name", nullable = false, length = 100) // Database constraint
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100) // Database constraint
    private String lastName;

    @Column(name = "middle_name", length = 100) // Optional field
    private String middleName;

    @Column(length = 2) // Suffix (e.g., Jr., Sr.)
    private String suffix;

    @Column(nullable = false) // Database constraint
    private Integer age;

    @Column(nullable = false, length = 10) // Database constraint
    private String gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false) // Database constraint
    private Status status;

    @Column(name = "complete_address", nullable = false) // Database constraint
    private String completeAddress;

    @Column(name = "birth_date", nullable = false) // Database constraint
    private LocalDate birthDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Purpose purpose;

    @Column(name = "contact_no", length = 11, nullable = false)
    private String contactNumber;

    public Resident(long id, Date dateAt, long nationalId, String firstName, String lastName,
                    String middleName, String suffix, Integer age, String gender, Status status,
                    String completeAddress, LocalDate birthDate, Purpose purpose, String contactNumber) {
        this.id = id;
        this.purpose = purpose;
        this.dateAt = dateAt;
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

    public Resident() {
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
