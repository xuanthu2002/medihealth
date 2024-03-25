package com.mad.medihealth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mad.medihealth.model.Schedule;
import com.mad.medihealth.repository.PrescriptionRepository;
import com.mad.medihealth.repository.ScheduleRepository;
import com.mad.medihealth.service.impl.ScheduleServiceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService implements ScheduleServiceImpl{
	
	private final ScheduleRepository scheduleRepository;
	private final PrescriptionRepository prescriptionRepository;
	@Override
	public List<Schedule> getListScheduleofToday(String user_id) {
		return scheduleRepository.getList(user_id);
	}


}
