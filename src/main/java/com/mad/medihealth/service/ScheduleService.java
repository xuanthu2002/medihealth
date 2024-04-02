package com.mad.medihealth.service;

import com.mad.medihealth.model.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getListScheduleofToday(String user_id);

    List<Schedule> getAllByUser(String userId);
}
