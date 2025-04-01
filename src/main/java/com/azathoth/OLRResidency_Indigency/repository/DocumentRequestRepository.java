package com.azathoth.OLRResidency_Indigency.repository;

import com.azathoth.OLRResidency_Indigency.model.DocumentRequest;
import com.azathoth.OLRResidency_Indigency.util.DocumentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DocumentRequestRepository extends JpaRepository<DocumentRequest, Long> {
    @Query("SELECT dr FROM DocumentRequest dr WHERE dr.status = :status AND dr.dueDate <= :date")
    List<DocumentRequest> findByStatusAndDate(
            @Param("status") DocumentStatus status,
            @Param("date") LocalDate date
    );
}
