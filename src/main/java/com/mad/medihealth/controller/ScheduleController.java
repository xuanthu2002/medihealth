package com.mad.medihealth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mad.medihealth.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {
	private final ScheduleService scheduleService;
	
	@GetMapping("/today")
	public ResponseEntity<?> getAllScheduleofToday(@RequestParam("user_id") String userId){
		return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getListScheduleofToday(userId));
	}
}
