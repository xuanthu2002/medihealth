package com.mad.medihealth.service.impl;

import java.time.LocalDate;
import java.util.List;

import com.mad.medihealth.model.Prescription;

public interface PrescriptionServiceImpl {
	List<Prescription> getStatDay(LocalDate date);
}
