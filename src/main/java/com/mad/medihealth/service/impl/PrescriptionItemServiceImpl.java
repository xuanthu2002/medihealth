package com.mad.medihealth.service.impl;

import com.mad.medihealth.exception.DataNotFoundException;
import com.mad.medihealth.model.PrescriptionItem;
import com.mad.medihealth.repository.PrescriptionItemRepository;
import com.mad.medihealth.repository.PrescriptionRepository;
import com.mad.medihealth.service.PrescriptionItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionItemServiceImpl implements PrescriptionItemService {

    @Autowired
    private PrescriptionItemRepository prescriptionItemRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public Iterable<PrescriptionItem> getAllByPrescription(Long prescriptionId) throws DataNotFoundException {
        if (!prescriptionRepository.existsById(prescriptionId)) {
            throw new DataNotFoundException("Thông tin đơn thuốc không tồn tại.");
        }
        return prescriptionItemRepository.findAllByPrescriptionId(prescriptionId);
    }

    @Override
    public PrescriptionItem addPrescriptionItem(PrescriptionItem prescriptionItem) throws DataNotFoundException {
        if (!prescriptionRepository.existsById(prescriptionItem.getPrescription().getId())) {
            throw new DataNotFoundException("Thông tin đơn thuốc không tồn tại.");
        }
        return prescriptionItemRepository.save(prescriptionItem);
    }

    @Override
    public void updatePrescriptionItem(PrescriptionItem prescriptionItem) throws DataNotFoundException {
        if (!prescriptionItemRepository.existsById(prescriptionItem.getId())) {
            throw new DataNotFoundException("Thông tin thuốc không tồn tại.");
        }
        prescriptionItemRepository.save(prescriptionItem);
    }

    @Override
    public void deletePrescriptionItem(Long id) throws DataNotFoundException {
        if (!prescriptionItemRepository.existsById(id)) {
            throw new DataNotFoundException("Thông tin thuốc không tồn tại.");
        }
        prescriptionItemRepository.deleteById(id);
    }
}
