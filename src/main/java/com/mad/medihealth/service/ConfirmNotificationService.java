package com.mad.medihealth.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.mad.medihealth.model.ConfirmNotification;
import com.mad.medihealth.model.Prescription;
import com.mad.medihealth.repository.ConfirmNotificationRepository;
import com.mad.medihealth.repository.PrescriptionRepository;
import com.mad.medihealth.service.impl.ConfirmNotificationServiceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ConfirmNotificationService implements ConfirmNotificationServiceImpl{
	private final ConfirmNotificationRepository confirmNotificationRepository;
	private final PrescriptionRepository prescriptionRepository;

	@Override
	public boolean saveConfirmNotification(Long presription_id, boolean isCheck, LocalTime time, String des) {
		try {
			ConfirmNotification confirmNotification = new ConfirmNotification();
			Prescription prescription = prescriptionRepository.findById(presription_id).orElse(null);
			if(prescription == null) {
				return false;
			}
			confirmNotification.setCheck(isCheck);
			confirmNotification.setTime(time);
			confirmNotification.setDes(des);
			confirmNotification.setDate(LocalDate.now());
			confirmNotification.setPrescription(prescription);
            confirmNotificationRepository.save(confirmNotification);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; 
        }
	}
	
}
