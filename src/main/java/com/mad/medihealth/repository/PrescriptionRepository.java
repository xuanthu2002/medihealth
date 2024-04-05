package com.mad.medihealth.repository;

import com.mad.medihealth.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    Iterable<Prescription> findAllByDrugUserId(Long id);
}