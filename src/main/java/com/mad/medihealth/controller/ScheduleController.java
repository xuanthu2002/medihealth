package com.mad.medihealth.controller;

import com.mad.medihealth.exception.DataNotFoundException;
import com.mad.medihealth.model.Schedule;
import com.mad.medihealth.service.ConfirmNotificationService;
import com.mad.medihealth.service.ScheduleService;
import com.mad.medihealth.util.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final ConfirmNotificationService confirmNotificationService;

    @GetMapping
    public ResponseEntity<?> getAllByUser(@RequestParam("uid") String userId) {
        List<Schedule> schedules = scheduleService.getListScheduleofToday(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        ResponseObject.builder()
                                .code(200)
                                .message("OK")
                                .data(schedules)
                                .build()
                );
    }

    @GetMapping("/today")
    public ResponseEntity<?> getAllScheduleofToday(@RequestParam("user_id") String userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        scheduleService.getListScheduleofToday(userId)
                );
    }

    @GetMapping("{id}/status")
    public ResponseEntity<?> checkStatus(@PathVariable("id") Long id) throws DataNotFoundException {
        boolean isActive = scheduleService.checkStatus(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        ResponseObject.builder()
                                .code(200)
                                .message("OK")
                                .data(isActive)
                                .build()
                );
    }

}
