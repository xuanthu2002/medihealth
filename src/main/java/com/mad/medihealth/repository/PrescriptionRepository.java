package com.mad.medihealth.repository;

import com.mad.medihealth.model.DrugUser;
import com.mad.medihealth.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    Iterable<Prescription> findAllByDrugUserId(Long drugUserId);
}