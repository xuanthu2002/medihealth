package com.mad.medihealth.controller;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mad.medihealth.service.impl.PrescriptionServiceImpl;
import com.mad.medihealth.util.ResponseObject;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/medihealth/stat")
public class PrescriptionController {
	private final PrescriptionServiceImpl prescriptionServiceImpl;
	
	@GetMapping("/day")
	public ResponseEntity<?> getStatDay(@RequestParam LocalDate date){
		return ResponseEntity.status(HttpStatus.OK)
				.body(
						new ResponseObject(
								200,
								"Successfully",
								prescriptionServiceImpl.getStatDay(date)
								));
	}
}
