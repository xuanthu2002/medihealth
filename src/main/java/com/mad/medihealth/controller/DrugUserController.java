package com.mad.medihealth.controller;

import com.mad.medihealth.exception.DataNotFoundException;
import com.mad.medihealth.model.DrugUser;
import com.mad.medihealth.service.DrugUserService;
import com.mad.medihealth.util.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("drug-user")
public class DrugUserController {

    @Autowired
    private DrugUserService drugUserService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllDrugUserByUser(@RequestParam("uid") String uid) {
        return ResponseEntity.status(HttpStatus.OK).body(drugUserService.getAllByUser(uid));
    }

    @GetMapping
    public ResponseEntity<?> getAllByUser(@RequestParam("uid") String uid) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        ResponseObject.builder()
                                .code(200)
                                .message("Ok")
                                .data(drugUserService.getAllByUser(uid))
                                .build()
                );
    }

    @PostMapping
    public ResponseEntity<?> addDrugUser(@RequestBody DrugUser drugUser) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        ResponseObject.builder()
                                .code(201)
                                .message("Created")
                                .data(drugUserService.addDrugUser(drugUser))
                                .build()
                );
    }

    @PutMapping
    public ResponseEntity<?> editDrugUser(@RequestBody DrugUser drugUser) {
        try {
            drugUserService.updateDrugUser(drugUser);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(
                            ResponseObject.builder()
                                    .code(200)
                                    .message("Update drug user information successfully.")
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
    public ResponseEntity<?> deleteDrugUser(@PathVariable("id") Long id) {
        try {
            drugUserService.deleteDrugUser(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(
                            ResponseObject.builder()
                                    .code(200)
                                    .message("Delete drug user information successfully.")
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
