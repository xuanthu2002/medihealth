package com.mad.medihealth.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.mad.medihealth.model.ConfirmNotification;
import com.mad.medihealth.model.Schedule;
import com.mad.medihealth.repository.ConfirmNotificationRepository;
import com.mad.medihealth.repository.ScheduleRepository;
import com.mad.medihealth.service.ConfirmNotificationService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ConfirmNotificationServiceImpl implements ConfirmNotificationService{
	private final ConfirmNotificationRepository confirmNotificationRepository;
	private final ScheduleRepository scheduleRepository;

	@Override
	public boolean saveConfirmNotification(Long schedule_id, boolean isCheck, LocalTime time, String des) {
		try {
			ConfirmNotification confirmNotification = new ConfirmNotification();
			Schedule schedule = scheduleRepository.findById(schedule_id).orElse(null);
			if(schedule == null) {
				return false;
			}
			confirmNotification.setCheck(isCheck);
			confirmNotification.setTime(time);
			confirmNotification.setDes(des);
			confirmNotification.setDate(LocalDate.now());
			confirmNotification.setSchedule(schedule);;
            confirmNotificationRepository.save(confirmNotification);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; 
        }
	}
	
}
