package com.mad.medihealth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mad.medihealth.model.DrugUser;
import com.mad.medihealth.repository.DrugUserRepository;
import com.mad.medihealth.service.DrugUserService;

@Service
public class DrugUserServiceImpl implements DrugUserService {
	
	@Autowired
    private DrugUserRepository drugUserRepository;
	
	@Override
    public Iterable<DrugUser> getAllByUser(String uid) {
        return drugUserRepository.findAllByUserId(uid);
    }

}
