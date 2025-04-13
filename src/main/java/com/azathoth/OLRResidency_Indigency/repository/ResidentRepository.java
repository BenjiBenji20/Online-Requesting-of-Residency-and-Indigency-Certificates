package com.azathoth.OLRResidency_Indigency.repository;

import com.azathoth.OLRResidency_Indigency.model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long> {

    // query for keyword search
    @Query("SELECT r FROM Resident r WHERE " +
            "LOWER(r.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.middleName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.suffix) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Resident> searchResidents(
            @Param("keyword") String keyword
    );
}
