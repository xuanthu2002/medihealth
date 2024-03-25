package com.mad.medihealth.repository;

import com.mad.medihealth.model.PrescriptionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionItemRepository extends JpaRepository<PrescriptionItem, Long> {
    Iterable<PrescriptionItem> findAllByPrescriptionId(Long prescriptionId);
}