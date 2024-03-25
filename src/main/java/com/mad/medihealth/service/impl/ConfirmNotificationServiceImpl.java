package com.mad.medihealth.service.impl;

import java.time.LocalTime;

public interface ConfirmNotificationServiceImpl {
	public boolean saveConfirmNotification(Long schedule_id, boolean isCheck, LocalTime time, String des);
}
