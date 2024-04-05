package com.mad.medihealth.controller;

import com.mad.medihealth.service.PrescriptionStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin
@RequestMapping("stat")
@RequiredArgsConstructor
public class PrescriptionStatController {

    private final PrescriptionStatService prescriptionStatService;

    @GetMapping("/day")
    public ResponseEntity<?> getPrescriptionStatDay(@RequestParam Long duid, @RequestParam LocalDate date) {
        return ResponseEntity.status(HttpStatus.OK).body(prescriptionStatService.getPrescriptionStatDayByDrugUserID(duid, date));
    }

    @GetMapping("/week")
    public ResponseEntity<?> getPrescriptionStatWeek(@RequestParam Long duid, @RequestParam LocalDate start, @RequestParam LocalDate end) {
        return ResponseEntity.status(HttpStatus.OK).body(prescriptionStatService.getPrescriptionStatWeekByDrugUserID(duid, start, end));
    }

    @GetMapping("/month")
    public ResponseEntity<?> getPrescriptionStatMonth(@RequestParam Long duid, @RequestParam LocalDate dayofmonth) {
        return ResponseEntity.status(HttpStatus.OK).body(prescriptionStatService.getPrescriptionStatMonthByDrugUserID(duid, dayofmonth));
    }
}
