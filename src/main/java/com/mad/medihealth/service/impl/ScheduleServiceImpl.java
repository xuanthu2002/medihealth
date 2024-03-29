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
	public void deleteSchedule(Long id) throws DataNotFoundException {
		Schedule schedule = scheduleRepository.findById(id).orElseThrow(
				() -> new DataNotFoundException("Không tìm thấy lịch uống thuốc với id: " + id)
		);
		schedule.setActive(false);
		scheduleRepository.save(schedule);
	}

	@Override
	public void undoSchedule(Long id) throws DataNotFoundException {
		Schedule schedule = scheduleRepository.findById(id).orElseThrow(
				() -> new DataNotFoundException("Không tìm thấy lịch uống thuốc với id: " + id)
		);
		schedule.setActive(true);
		scheduleRepository.save(schedule);
	}

}
