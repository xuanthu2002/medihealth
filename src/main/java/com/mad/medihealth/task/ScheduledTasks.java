package com.mad.medihealth.task;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mad.medihealth.model.ConfirmNotification;
import com.mad.medihealth.model.Schedule;
import com.mad.medihealth.repository.ConfirmNotificationRepository;
import com.mad.medihealth.repository.ScheduleRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {
	
	private final ScheduleRepository scheduleRepository;
	private final ConfirmNotificationRepository confirmNotificationRepository;

    @Scheduled(cron = "59 59 23 * * *")
    public void doSomething() {
    	List<Schedule> scheduleList = (List<Schedule>) scheduleRepository.findAllByIsActiveIsTrueAndPrescriptionDrugUserIsActiveIsTrue();
    	scheduleList.forEach(schedule -> {
    		ConfirmNotification confirmNotification = new ConfirmNotification();
    		confirmNotification.setCheck(false);
            confirmNotification.setTime(schedule.getTime());
            confirmNotification.setDes("Quên uống");
            confirmNotification.setDate(LocalDate.now());
            confirmNotification.setSchedule(schedule);
            confirmNotificationRepository.save(confirmNotification);
    	});
    }
}

