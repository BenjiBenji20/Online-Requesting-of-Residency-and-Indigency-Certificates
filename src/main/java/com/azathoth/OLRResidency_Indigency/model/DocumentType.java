package com.azathoth.OLRResidency_Indigency.model;

public enum DocumentType {
    BARANGAY_CLEARANCE(2),
    RESIDENCY(2),
    INDIGENCY(2);

    private final int processingHours;
    DocumentType(int getProcessingHours) {
        this.processingHours = getProcessingHours;
    }

    public int getProcessingHours() {
        return this.processingHours;
    }
}
