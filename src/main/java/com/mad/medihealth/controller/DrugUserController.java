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
                                .message("OK")
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
                                .message("Thêm người dùng thuốc thành công.")
                                .data(drugUserService.addDrugUser(drugUser))
                                .build()
                );
    }

    @PutMapping
    public ResponseEntity<?> editDrugUser(@RequestBody DrugUser drugUser) throws DataNotFoundException {
        drugUserService.updateDrugUser(drugUser);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        ResponseObject.builder()
                                .code(200)
                                .message("Cập nhật thông tin người dùng thuốc thành công.")
                                .build()
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDrugUser(@PathVariable("id") Long id) throws DataNotFoundException {
        drugUserService.deleteDrugUser(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        ResponseObject.builder()
                                .code(200)
                                .message("Xóa thông tin người dùng thuốc thành công.")
                                .build()
                );
    }

}
