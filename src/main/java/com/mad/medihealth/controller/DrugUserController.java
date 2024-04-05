package com.mad.medihealth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mad.medihealth.service.DrugUserService;

@RestController
@RequestMapping("drug-user")
public class DrugUserController {
	
 	@Autowired
    private DrugUserService drugUserService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllDrugUserByUser(@RequestParam("uid") String uid) {
        return ResponseEntity.status(HttpStatus.OK).body(drugUserService.getAllByUser(uid));
    }
}
