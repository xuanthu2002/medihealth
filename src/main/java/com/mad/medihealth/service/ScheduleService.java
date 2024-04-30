package com.mad.medihealth.service;

import com.mad.medihealth.exception.DataNotFoundException;
import com.mad.medihealth.model.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    List<Schedule> getListScheduleofToday(String user_id);

    Iterable<Schedule> getAllByUserId(String uid);

    Schedule getById(Long id) throws DataNotFoundException;
}
