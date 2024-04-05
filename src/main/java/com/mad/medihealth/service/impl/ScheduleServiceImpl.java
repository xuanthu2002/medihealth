package com.mad.medihealth.service.impl;

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
    public List<Schedule> getAllByUser(String userId) {
        List<Schedule> schedules = (List<Schedule>) scheduleRepository.findAllByPrescriptionDrugUserUserIdOrderByTimeAsc(userId);
        schedules.forEach((schedule) -> {
            schedule.setConfirmNotifications(null);
        });
        return schedules;
    }

}
