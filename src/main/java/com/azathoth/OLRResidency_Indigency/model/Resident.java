package com.azathoth.OLRResidency_Indigency.model;

import com.azathoth.OLRResidency_Indigency.util.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "malabon_resident")
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // auto generate date when user data is entered in db
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date dateAt;

    @Column(name = "first_name", nullable = false)
    @Pattern(regexp = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}", message = "Cannot allowed special characters or space at the beginning")
    @NotNull(message = "Name field cannot be empty")
    @Size(min = 2, max = 100, message = "Name must be between 2-100 letters")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Pattern(regexp = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}", message = "Cannot allowed special characters or space at the beginning")
    @NotNull(message = "Name field cannot be empty")
    @Size(min = 2, max = 100, message = "Name must be between 2-100 letters")
    private String lastName;

    @Column(name = "middle_name")
    @Pattern(regexp = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}", message = "Cannot allowed special characters or space at the beginning")
    @Size(min = 2, max = 100, message = "Name must be between 2-100 letters")
    private String middleName;

    @NotNull(message = "Age field cannot be empty")
    @Size(min = 1, max = 3)
    @Min(value = 0, message = "Age must be positive")
    @Column(nullable = false)
    private Integer age;

    @NotNull(message = "Gender field cannot be empty")
    @Size(min = 10, max = 100)
    @Column(nullable = false)
    private String gender;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status field cannot be empty")
    @Column(nullable = false)
    private Status status;

    @NotNull(message = "Address field cannot be empty")
    @Column(name = "complete_address", nullable = false)
    private String address;

    @NotNull(message = "Birth date field cannot be empty")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    public Resident(long id, String firstName, String lastName, String middleName, Integer age, String gender, Status status, String address, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.age = age;
        this.gender = gender;
        this.status = status;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
