package com.mad.medihealth.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mad.medihealth.model.Prescription;
import com.mad.medihealth.repository.PrescriptionRepository;
import com.mad.medihealth.service.impl.PrescriptionServiceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PrescriptionService implements PrescriptionServiceImpl{
	private final PrescriptionRepository prescriptionRepository;
	
	@Override
	public List<Prescription> getStatDay(LocalDate date) {
		
		return prescriptionRepository.findAllPrescriptionsWithSchedulesAndConfirmationsOnSpecificDate(date);
	}
	
}
