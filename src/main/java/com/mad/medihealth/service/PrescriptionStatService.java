package com.mad.medihealth.service;

import com.mad.medihealth.model.Prescription;
import com.mad.medihealth.model.PrescriptionStat;

import java.time.LocalDate;
import java.util.List;

public interface PrescriptionStatService {
    List<Prescription> getPrescriptionStatDayByDrugUserID(Long duid, LocalDate date);

    List<Prescription> getPrescriptionStatWeekByDrugUserID(Long duid, LocalDate start, LocalDate end);

    List<PrescriptionStat> getPrescriptionStatMonthByDrugUserID(Long duid, LocalDate dayofmonth);
}
