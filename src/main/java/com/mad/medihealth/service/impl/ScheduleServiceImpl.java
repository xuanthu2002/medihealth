package com.mad.medihealth.service.impl;

import com.mad.medihealth.exception.DataNotFoundException;
import com.mad.medihealth.model.Schedule;
import com.mad.medihealth.repository.ScheduleRepository;
import com.mad.medihealth.service.ScheduleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> getListScheduleofToday(String user_id) {
        List<Schedule> schedules = scheduleRepository.getListScheduleofToday(user_id);
        schedules.forEach(schedule -> schedule.setConfirmNotifications(null));
        schedules.forEach(schedule -> schedule.getPrescription().setSchedules(null));
        return schedules;
    }

    @Override
    public boolean checkStatus(Long id) throws DataNotFoundException {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () ->  new DataNotFoundException("Thông tin lịch uống thuốc không tồn tại.")
        );

        return schedule.isActive()
                && schedule.getPrescription().isActive()
                && schedule.getPrescription().getDrugUser().isActive();
    }

}
