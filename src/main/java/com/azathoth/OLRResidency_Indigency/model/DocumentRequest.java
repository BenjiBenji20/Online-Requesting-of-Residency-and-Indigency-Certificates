package com.azathoth.OLRResidency_Indigency.model;

import com.azathoth.OLRResidency_Indigency.util.DocumentStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "document_request")
public class DocumentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "resident_id")
    private Resident resident;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false)
    private DocumentType documentType;

    @Column(name = "request_date", nullable = false)
    private LocalDate requestDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private DocumentStatus status = DocumentStatus.PENDING;

    public DocumentRequest(Long id, Resident resident, DocumentType documentType, LocalDate requestDate, LocalDate dueDate, DocumentStatus status) {
        this.id = id;
        this.resident = resident;
        this.documentType = documentType;
        this.requestDate = requestDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public DocumentRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public DocumentStatus getStatus() {
        return status;
    }

    public void setStatus(DocumentStatus status) {
        this.status = status;
    }
}
