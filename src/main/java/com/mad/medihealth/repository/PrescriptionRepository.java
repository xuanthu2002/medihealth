package com.mad.medihealth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mad.medihealth.model.Prescription;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long>{
	List<Prescription> findAllByDrugUserId(Long duid);
}
