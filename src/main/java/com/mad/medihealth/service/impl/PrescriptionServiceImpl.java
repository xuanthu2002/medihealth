package com.mad.medihealth.service.impl;

import com.mad.medihealth.exception.DataNotFoundException;
import com.mad.medihealth.model.Prescription;
import com.mad.medihealth.model.PrescriptionItem;
import com.mad.medihealth.model.Schedule;
import com.mad.medihealth.repository.DrugUserRepository;
import com.mad.medihealth.repository.PrescriptionItemRepository;
import com.mad.medihealth.repository.PrescriptionRepository;
import com.mad.medihealth.repository.ScheduleRepository;
import com.mad.medihealth.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private PrescriptionItemRepository prescriptionItemRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
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
        Prescription original = prescriptionRepository.findById(prescription.getId())
                .orElseThrow(() -> new DataNotFoundException("Thông tin đơn thuốc không tồn tại"));

        original.setTitle(prescription.getTitle());
        original.setActive(prescription.isActive());

        List<PrescriptionItem> oldPrescriptionItems = new ArrayList<>(original.getPrescriptionItems());
        List<PrescriptionItem> newPrescriptionItems = prescription.getPrescriptionItems();
        oldPrescriptionItems.forEach((oldItem) -> {
            if (newPrescriptionItems.stream().noneMatch(
                    (newItem) -> newItem.getName().equals(oldItem.getName())
                            && newItem.getNote().equals(oldItem.getNote())
            )) {
                original.getPrescriptionItems().remove(oldItem);
                prescriptionItemRepository.deleteById(oldItem.getId());
            }
        });
        newPrescriptionItems.forEach((newItem) -> {
            if (oldPrescriptionItems.stream().noneMatch(
                    (oldItem) -> newItem.getName().equals(oldItem.getName())
                            && newItem.getNote().equals(oldItem.getNote()))) {
                original.getPrescriptionItems().add(newItem);
            }
        });

        List<Schedule> oldSchedules = new ArrayList<>(original.getSchedules());
        List<Schedule> newSchedules = prescription.getSchedules();
        oldSchedules.forEach((oldSchedule) -> {
            if (newSchedules.stream().noneMatch(
                    (newSchedule) -> oldSchedule.getTime().equals(newSchedule.getTime()))) {
                original.getSchedules().remove(oldSchedule);
                scheduleRepository.deleteById(oldSchedule.getId());
            }
        });
        newSchedules.forEach((newSchedule) -> {
            if (oldSchedules.stream().noneMatch(
                    (oldSchedule) -> oldSchedule.getTime().equals(newSchedule.getTime()))) {
                original.getSchedules().add(newSchedule);
            }
        });

        prescriptionRepository.save(original);
    }


    @Override
    public void deletePrescription(Long id) throws DataNotFoundException {
        if (!prescriptionRepository.existsById(id)) {
            throw new DataNotFoundException("Thông tin đơn thuốc không tồn tại.");
        }
        prescriptionRepository.deleteById(id);
    }
}
