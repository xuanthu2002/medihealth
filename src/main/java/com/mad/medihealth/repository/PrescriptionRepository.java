package com.mad.medihealth.repository;

import com.mad.medihealth.model.Prescription;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
	@Query(value = "SELECT DISTINCT p.* FROM prescriptions p " +
            "JOIN schedules s ON p.id = s.prescription_id " +
            "WHERE EXISTS (SELECT 1 FROM confirm_notifications c WHERE s.id = c.schedule_id AND c.date = :specificDate)", nativeQuery = true)
    List<Prescription> findAllPrescriptionsWithSchedulesAndConfirmationsOnSpecificDate(@Param("specificDate") LocalDate specificDate);

}