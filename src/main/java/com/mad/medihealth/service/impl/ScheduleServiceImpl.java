package com.mad.medihealth.service.impl;

import java.util.List;

import com.mad.medihealth.exception.DataNotFoundException;
import org.springframework.stereotype.Service;

import com.mad.medihealth.model.Schedule;
import com.mad.medihealth.repository.ScheduleRepository;
import com.mad.medihealth.service.ScheduleService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
	
	private final ScheduleRepository scheduleRepository;
	@Override
	public List<Schedule> getListScheduleofToday(String user_id) {
		return scheduleRepository.getList(user_id);
	}

	@Override
	public List<Schedule> getAllByUser(String userId) {
		List<Schedule> schedules = (List<Schedule>) scheduleRepository.findAllByPrescriptionDrugUserUserIdOrderByTimeAsc(userId);
		schedules.forEach((schedule) -> {
			schedule.setConfirmNotifications(null);
		});
		return schedules;
	}

}
