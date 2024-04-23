package com.mad.medihealth.service;

import com.mad.medihealth.exception.DataNotFoundException;
import com.mad.medihealth.model.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getListScheduleofToday(String user_id);

    Iterable<Schedule> getAllByUserId(String uid);

    boolean checkStatus(Long id) throws DataNotFoundException;
}
