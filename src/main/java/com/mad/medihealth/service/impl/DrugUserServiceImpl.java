package com.mad.medihealth.service.impl;

import com.mad.medihealth.exception.DataNotFoundException;
import com.mad.medihealth.model.DrugUser;
import com.mad.medihealth.repository.DrugUserRepository;
import com.mad.medihealth.service.DrugUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrugUserServiceImpl implements DrugUserService {

    @Autowired
    private DrugUserRepository drugUserRepository;

    @Override
    public Iterable<DrugUser> getAllByUser(String uid) {
        return drugUserRepository.findAllByUserId(uid);
    }

    @Override
    public DrugUser addDrugUser(DrugUser drugUser) {
        return drugUserRepository.save(drugUser);
    }

    @Override
    public void updateDrugUser(DrugUser drugUser) throws DataNotFoundException {
        if(!drugUserRepository.existsById(drugUser.getId())) {
            throw new DataNotFoundException("Thông tin người dùng thuốc không tồn tại.");
        }
        drugUserRepository.save(drugUser);
    }

    @Override
    public void deleteDrugUser(Long id) throws DataNotFoundException {
        DrugUser drugUser = drugUserRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Thông tin người dùng thuốc không tồn tại.")
        );
        drugUser.setActive(false);
        drugUserRepository.save(drugUser);
    }
}
