package com.mad.medihealth.repository;

import com.mad.medihealth.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    Iterable<Prescription> findAllByDrugUserId(Long id);

    Iterable<Prescription> findAllByDrugUserIdAndIsActiveIsTrue(Long id);

    boolean existsByIdAndIsActiveIsTrue(Long id);

    Optional<Prescription> findByIdAndIsActiveIsTrue(Long id);
}