package com.mad.medihealth.service;

import java.util.List;

import com.mad.medihealth.exception.DataNotFoundException;
import com.mad.medihealth.model.Schedule;

public interface ScheduleService {
	List<Schedule> getListScheduleofToday(String user_id);
	void deleteSchedule(Long id) throws DataNotFoundException;
	void undoSchedule(Long id) throws DataNotFoundException;
}
