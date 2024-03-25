package com.mad.medihealth.controller;

import com.mad.medihealth.exception.DataNotFoundException;
import com.mad.medihealth.model.PrescriptionItem;
import com.mad.medihealth.service.PrescriptionItemService;
import com.mad.medihealth.util.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("prescription-item")
public class PrescriptionItemController {

    @Autowired
    private PrescriptionItemService prescriptionItemService;

    @GetMapping
    public ResponseEntity<?> getAllByPrescription(@RequestParam("p_id") Long prescriptionId) {
        try {
            List<PrescriptionItem> results = (List<PrescriptionItem>) prescriptionItemService.getAllByPrescription(prescriptionId);
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

    @PostMapping
    public ResponseEntity<?> addPrescriptionItem(@RequestBody PrescriptionItem prescriptionItem) {
        try {
            PrescriptionItem newPrescriptionItem = prescriptionItemService.addPrescriptionItem(prescriptionItem);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(
                            ResponseObject.builder()
                                    .code(201)
                                    .message("Ok")
                                    .data(newPrescriptionItem)
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
    public ResponseEntity<?> editPrescriptionItem(@RequestBody PrescriptionItem prescriptionItem) {
        try {
            prescriptionItemService.updatePrescriptionItem(prescriptionItem);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(
                            ResponseObject.builder()
                                    .code(200)
                                    .message("Ok")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrescriptionItem(@PathVariable("id") Long id) {
        try {
            prescriptionItemService.deletePrescriptionItem(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(
                            ResponseObject.builder()
                                    .code(200)
                                    .message("Ok")
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
