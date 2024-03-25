package com.mad.medihealth.service;

import com.mad.medihealth.exception.DataNotFoundException;
import com.mad.medihealth.model.PrescriptionItem;

public interface PrescriptionItemService {
    Iterable<PrescriptionItem> getAllByPrescription(Long prescriptionId) throws DataNotFoundException;

    PrescriptionItem addPrescriptionItem(PrescriptionItem prescriptionItem) throws DataNotFoundException;

    void updatePrescriptionItem(PrescriptionItem prescriptionItem) throws DataNotFoundException;

    void deletePrescriptionItem(Long id) throws DataNotFoundException;
}
