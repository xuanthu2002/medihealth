package com.mad.medihealth.service.impl;

import java.util.List;

import com.mad.medihealth.model.Schedule;

public interface ScheduleServiceImpl {
	List<Schedule> getListScheduleofToday(String user_id);
}
