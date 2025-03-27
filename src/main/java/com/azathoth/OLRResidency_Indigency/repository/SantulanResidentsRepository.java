package com.azathoth.OLRResidency_Indigency.repository;

import com.azathoth.OLRResidency_Indigency.model.SantulanResidents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SantulanResidentsRepository extends JpaRepository<SantulanResidents, Long> {
    // find existing resident using its unique national id
    @Query("SELECT COUNT(r) > 0 FROM SantulanResidents r WHERE r.nationalId = :nationalId")
    boolean findResidentByNationalId(@Param("nationalId") long nationalId);

    // find existing resident by combination of unique information
    // this will be used for requesting resident for document like residency and indigency
    @Query("SELECT COUNT(r) > 0 FROM SantulanResidents r WHERE " +
            "r.nationalId = :nationalId AND " +
            "r.firstName = :firstName AND " +
            "r.lastName = :lastName")
    boolean findResidentByInfo(@Param("nationalId") long nationalId,
                               @Param("firstName") String firstName,
                               @Param("lastName") String lastName
                               );
}
