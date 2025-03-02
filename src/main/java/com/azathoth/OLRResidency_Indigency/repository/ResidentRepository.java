package com.azathoth.OLRResidency_Indigency.repository;

import com.azathoth.OLRResidency_Indigency.model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long> {
}
