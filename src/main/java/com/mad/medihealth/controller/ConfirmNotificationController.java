package com.mad.medihealth.controller;

import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mad.medihealth.service.ConfirmNotificationService;
import com.mad.medihealth.util.ResponseMessage;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ConfirmNotificationController {
	

	private final ConfirmNotificationService confirmNotificationService;
	
	@PostMapping("/confirm")
	public ResponseEntity<?> saveConfirmNotification_Confirm(@RequestParam("schedule_id") Long schedule_id, @RequestParam("time") LocalTime time){
		if(confirmNotificationService.saveConfirmNotification(schedule_id, true, time, null)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMessage("Confirm schedule successfully"));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Failed to confirm schedule"));
	}
	
	@PostMapping("/skip")
	public ResponseEntity<?> saveConfirmNotification_Skip(@RequestParam("schedule_id") Long schedule_id, @RequestParam("time") LocalTime time, @RequestParam("des")String des){
		if(confirmNotificationService.saveConfirmNotification(schedule_id, false, time, des)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMessage("Skip schedule successfully"));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Failed to skip schedule"));
	}
}
