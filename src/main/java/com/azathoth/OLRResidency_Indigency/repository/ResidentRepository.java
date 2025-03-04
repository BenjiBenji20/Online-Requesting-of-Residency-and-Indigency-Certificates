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
            "(:firstName IS NULL OR r.firstName LIKE %:firstName%) AND " +
            "(:lastName IS NULL OR r.lastName LIKE %:lastName%) AND " +
            "(:middleName IS NULL OR r.middleName LIKE %:middleName%) AND " +
            "(:suffix IS NULL OR r.suffix LIKE %:suffix%)")
    List<Resident> searchResidents(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("middleName") String middleName,
            @Param("suffix") String suffix
    );
}
