package com.mad.medihealth.repository;

import com.mad.medihealth.model.Prescription;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mad.medihealth.model.Prescription;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long>{
    Iterable<Prescription> findAllByDrugUserId(Long id);
}