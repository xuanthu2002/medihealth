package com.mad.medihealth.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mad.medihealth.model.Prescription;
import com.mad.medihealth.model.PrescriptionStat;
import com.mad.medihealth.repository.ConfirmNotificationRepository;
import com.mad.medihealth.repository.PrescriptionRepository;
import com.mad.medihealth.repository.PrescriptionStatRepository;
import com.mad.medihealth.service.PrescriptionStatService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PrescriptionStatServiceImpl implements PrescriptionStatService{
	
	private final PrescriptionRepository prescriptionRepository;
	private final ConfirmNotificationRepository confirmNotificationRepository;
	private final PrescriptionStatRepository prescriptionStatRepository;
	
	@Override
	public List<Prescription> getPrescriptionStatDayByDrugUserID(Long duid, LocalDate date) {
		List<Prescription> list = prescriptionRepository.findAllByDrugUserId(duid);
		list.forEach(prescription -> prescription
				.getSchedules().forEach(schedule -> schedule
						.setConfirmNotifications(confirmNotificationRepository.findConfirmNotificationByScheduleIdAndDate(schedule.getId(), date))));
		list.removeIf(prescription -> prescription.getSchedules().stream().allMatch(schedule -> schedule.getConfirmNotifications().isEmpty()));
		list.forEach(prescription -> prescription.getSchedules().removeIf(schedule -> (schedule.getConfirmNotifications().isEmpty() && !schedule.isActive())));
		return list;
	}

	@Override
	public List<Prescription> getPrescriptionStatWeekByDrugUserID(Long duid, LocalDate start, LocalDate end) {
		List<Prescription> list = prescriptionRepository.findAllByDrugUserId(duid);
		list.forEach(prescription -> prescription
				.getSchedules().forEach(schedule -> schedule
						.setConfirmNotifications(confirmNotificationRepository.getConfirmNotificationByScheduldeIdAndWeek(schedule.getId(), start, end))));
		if(start.isBefore(LocalDate.now())) {
			list.removeIf(prescription -> prescription.getSchedules().stream().allMatch(schedule -> schedule.getConfirmNotifications().isEmpty())); 
		}
		list.forEach(prescription -> prescription.getSchedules().removeIf(schedule -> (schedule.getConfirmNotifications().isEmpty() && !schedule.isActive())));
		return list;
	}

	@Override
	public List<PrescriptionStat> getPrescriptionStatMonthByDrugUserID(Long duid, LocalDate dayofmonth) {
		List<PrescriptionStat> list = prescriptionStatRepository.getPrescriptionStatByDrugUserIdAndMonth(duid, dayofmonth);
		return list;
	}
}
