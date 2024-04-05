package com.mad.medihealth.service.impl;

import java.util.List;

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
		List<Schedule> schedules = scheduleRepository.getListScheduleofToday(user_id);
		schedules.forEach(schedule -> schedule.setConfirmNotifications(null));
		schedules.forEach(schedule -> schedule.getPrescription().setSchedules(null));
		return schedules;
	}

}
