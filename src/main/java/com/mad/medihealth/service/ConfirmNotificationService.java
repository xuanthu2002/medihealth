package com.mad.medihealth.service;

import java.time.LocalTime;

public interface ConfirmNotificationService {
    public boolean saveConfirmNotification(Long schedule_id, boolean isCheck, LocalTime time, String des);
}
