package com.mad.medihealth.service;

import com.mad.medihealth.exception.DataNotFoundException;
import com.mad.medihealth.model.DrugUser;

public interface DrugUserService {
	Iterable<DrugUser> getAllByUser(String uid);

    DrugUser addDrugUser(DrugUser drugUser);

    void updateDrugUser(DrugUser drugUser) throws DataNotFoundException;

    void deleteDrugUser(Long id) throws DataNotFoundException;
}
