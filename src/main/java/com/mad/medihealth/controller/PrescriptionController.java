package com.mad.medihealth.controller;

import com.mad.medihealth.exception.DataNotFoundException;
import com.mad.medihealth.model.Prescription;
import com.mad.medihealth.service.PrescriptionService;
import com.mad.medihealth.util.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("prescription")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping
    public ResponseEntity<?> getAllByDrugUser(@RequestParam("d_uid") Long drugUserId) {
        try {
            List<Prescription> results = (List<Prescription>) prescriptionService.getAllByDrugUser(drugUserId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(
                            ResponseObject.builder()
                                    .code(200)
                                    .message("Ok")
                                    .data(results)
                                    .build()
                    );
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            ResponseObject.builder()
                                    .code(404)
                                    .message(e.getMessage())
                                    .build()
                    );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            Prescription result = prescriptionService.getById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(
                            ResponseObject.builder()
                                    .code(200)
                                    .message("Ok")
                                    .data(result)
                                    .build()
                    );
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            ResponseObject.builder()
                                    .code(404)
                                    .message(e.getMessage())
                                    .build()
                    );
        }
    }

    @PostMapping
    public ResponseEntity<?> addPrescription(@RequestBody Prescription prescription) {
        try {
            Prescription newPrescription = prescriptionService.addPrescription(prescription);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(
                            ResponseObject.builder()
                                    .code(201)
                                    .message("Ok")
                                    .data(newPrescription)
                                    .build()
                    );
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                    .body(
                            ResponseObject.builder()
                                    .code(501)
                                    .message(e.getMessage())
                                    .build()
                    );
        }
    }

    @PutMapping
    public ResponseEntity<?> editPrescription(@RequestBody Prescription prescription) {
        try {
            prescriptionService.updatePrescription(prescription);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(
                            ResponseObject.builder()
                                    .code(200)
                                    .message("Update prescription information successfully.")
                                    .build()
                    );
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                    .body(
                            ResponseObject.builder()
                                    .code(501)
                                    .message(e.getMessage())
                                    .build()
                    );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrescription(@PathVariable("id") Long id) {
        try {
            prescriptionService.deletePrescription(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(
                            ResponseObject.builder()
                                    .code(200)
                                    .message("Delete prescription information successfully.")
                                    .build()
                    );
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            ResponseObject.builder()
                                    .code(404)
                                    .message(e.getMessage())
                                    .build()
                    );
        }
    }

}
