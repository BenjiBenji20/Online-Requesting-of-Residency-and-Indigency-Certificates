package com.azathoth.OLRResidency_Indigency.model;

import com.azathoth.OLRResidency_Indigency.util.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "malabon_resident")
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    public Resident(long id, Date dateAt, String firstName, String lastName, String middleName, String suffix, Integer age, String gender, Status status, String completeAddress, LocalDate birthDate) {
        this.id = id;
        this.dateAt = dateAt;
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

    public Resident() {
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
