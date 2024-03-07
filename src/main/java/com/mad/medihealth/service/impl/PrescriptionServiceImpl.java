package com.mad.medihealth.service.impl;

import com.mad.medihealth.exception.DataNotFoundException;
import com.mad.medihealth.model.Prescription;
import com.mad.medihealth.repository.DrugUserRepository;
import com.mad.medihealth.repository.PrescriptionRepository;
import com.mad.medihealth.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private DrugUserRepository drugUserRepository;

    @Override
    public Iterable<Prescription> getAllByDrugUser(Long drugUserId) throws DataNotFoundException {
        if (!drugUserRepository.existsById(drugUserId)) {
            throw new DataNotFoundException("Thông tin người dùng thuốc không tồn tại.");
        }
        return prescriptionRepository.findAllByDrugUserId(drugUserId);
    }

    @Override
    public Prescription getById(Long id) throws DataNotFoundException {
        Optional<Prescription> prescriptionOpt = prescriptionRepository.findById(id);
        if (prescriptionOpt.isEmpty()) {
            throw new DataNotFoundException("Thông tin đơn thuốc không tồn tại.");
        }
        return prescriptionOpt.get();
    }

    @Override
    public Prescription addPrescription(Prescription prescription) throws DataNotFoundException {
        if (!drugUserRepository.existsById(prescription.getDrugUser().getId())) {
            throw new DataNotFoundException("Thông tin người dùng thuốc không tồn tại.");
        }
        return prescriptionRepository.save(prescription);
    }

    @Override
    public void updatePrescription(Prescription prescription) throws DataNotFoundException {
        if (!prescriptionRepository.existsById(prescription.getId())) {
            throw new DataNotFoundException("Thông tin đơn thuốc không tồn tại.");
        }
        prescriptionRepository.save(prescription);
    }

    @Override
    public void deletePrescription(Long id) throws DataNotFoundException {
        if (!prescriptionRepository.existsById(id)) {
            throw new DataNotFoundException("Thông tin đơn thuốc không tồn tại.");
        }
        prescriptionRepository.deleteById(id);
    }
}
