package com.azathoth.OLRResidency_Indigency.model;

public enum DocumentType {
    BARANGAY_CLEARANCE(2),
    RESIDENCY(3),
    INDIGENCY(3);

    private final int processingDays;
    DocumentType(int processingDays) {
        this.processingDays = processingDays;
    }

    public int getProcessingDays() {
        return this.processingDays;
    }
}
