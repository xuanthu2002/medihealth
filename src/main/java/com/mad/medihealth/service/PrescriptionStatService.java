package com.mad.medihealth.service;

import java.time.LocalDate;
import java.util.List;

import com.mad.medihealth.model.Prescription;
import com.mad.medihealth.model.PrescriptionStat;

public interface PrescriptionStatService {
	List<Prescription> getPrescriptionStatDayByDrugUserID(Long duid, LocalDate date);
	List<Prescription> getPrescriptionStatWeekByDrugUserID(Long duid, LocalDate start, LocalDate end);
	List<PrescriptionStat> getPrescriptionStatMonthByDrugUserID(Long duid, LocalDate dayofmonth);
}
