package com.mad.medihealth.service;

import com.mad.medihealth.exception.DataNotFoundException;
import com.mad.medihealth.model.Prescription;

public interface PrescriptionService {
    Iterable<Prescription> getAllByDrugUser(Long drugUserId) throws DataNotFoundException;

    Prescription getById(Long id) throws DataNotFoundException;

    Prescription addPrescription(Prescription prescription) throws DataNotFoundException;

    void updatePrescription(Prescription prescription) throws DataNotFoundException;

    void deletePrescription(Long id) throws DataNotFoundException;
}
