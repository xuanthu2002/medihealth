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
import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private DrugUserRepository drugUserRepository;
    @Autowired
    private PrescriptionItemRepository prescriptionItemRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Iterable<Prescription> getAllByDrugUser(Long drugUserId) throws DataNotFoundException {
        if (!drugUserRepository.existsByIdAndIsActiveIsTrue(drugUserId)) {
            throw new DataNotFoundException("Thông tin người dùng thuốc không tồn tại.");
        }
        List<Prescription> prescriptions = (List<Prescription>) prescriptionRepository.findAllByDrugUserIdAndIsActiveIsTrue(drugUserId);
        prescriptions.forEach(prescription -> {
            List<Schedule> schedules = (List<Schedule>) scheduleRepository.findAllByPrescriptionIdAndIsActiveIsTrue(prescription.getId());
            schedules.forEach(schedule -> schedule.setConfirmNotifications(null));
            prescription.setSchedules(schedules);
        });
        return prescriptions;
    }

    @Override
    public Prescription getById(Long id) throws DataNotFoundException {
        Prescription prescription = prescriptionRepository.findByIdAndIsActiveIsTrue(id).orElseThrow(
                () -> new DataNotFoundException("Thông tin đơn thuốc không tồn tại")
        );
        List<Schedule> schedules = (List<Schedule>) scheduleRepository.findAllByPrescriptionIdAndIsActiveIsTrue(prescription.getId());
        schedules.forEach(schedule -> schedule.setConfirmNotifications(null));
        prescription.setSchedules(schedules);
        return prescription;
    }

    @Override
    public Prescription addPrescription(Prescription prescription) throws DataNotFoundException {
        if (!drugUserRepository.existsByIdAndIsActiveIsTrue(prescription.getDrugUser().getId())) {
            throw new DataNotFoundException("Thông tin người dùng thuốc không tồn tại.");
        }

        prescription.getPrescriptionItems().forEach((item) -> item.setPrescription(prescription));
        prescription.getSchedules().forEach((schedule) -> {
            schedule.setPrescription(prescription);
            schedule.setActive(true);
        });

        return prescriptionRepository.save(prescription);
    }

    @Override
    public void updatePrescription(Prescription prescription) throws DataNotFoundException {
        Prescription original = prescriptionRepository.findByIdAndIsActiveIsTrue(prescription.getId())
                .orElseThrow(() -> new DataNotFoundException("Thông tin đơn thuốc không tồn tại"));

        original.setTitle(prescription.getTitle());
        original.setActive(prescription.isActive());

        // Xóa items cũ không có trong items mới
        List<PrescriptionItem> oldPrescriptionItems = new ArrayList<>(original.getPrescriptionItems());
        oldPrescriptionItems.forEach((oldItem) -> {
            if (!prescription.getPrescriptionItems().contains(oldItem)) {
                original.getPrescriptionItems().remove(oldItem);
                prescriptionItemRepository.deleteById(oldItem.getId());
            }
        });
        // Thêm items mới không có trong items cũ
        prescription.getPrescriptionItems().forEach((item) -> {
            if (original.getPrescriptionItems().stream().noneMatch(
                    (oldItem) -> oldItem.getName().equals(item.getName())
                            && oldItem.getNote().equals(item.getNote())
            )) {
                item.setPrescription(original);
                original.getPrescriptionItems().add(item);
            }
        });

        List<Schedule> newSchedules = prescription.getSchedules();
        original.getSchedules().forEach((schedule) -> {
            // Nếu lịch cũ không có id trong danh sách lịch mới (không có sửa) -> xóa
            if (newSchedules.stream().noneMatch((newSchedule) -> newSchedule.getId() == schedule.getId())) {
                schedule.setActive(false);
            }
            // Nếu lịch cũ có id trong lịch mới -> hành động cập nhật
            newSchedules.stream()
                    .filter((newSchedule) -> newSchedule.getId() == schedule.getId())
                    .findAny().ifPresent((newSchedule) -> {
                        schedule.setTime(newSchedule.getTime());
                        newSchedules.remove(newSchedule);
                    });
            // Nếu lịch mới có thời gian bằng với 1 thời gian đã xóa trước đó -> khôi phục
            newSchedules.stream()
                    .filter((newSchedule) -> newSchedule.getTime().equals(schedule.getTime()))
                    .findAny().ifPresent((newSchedule) -> {
                        schedule.setActive(true);
                        newSchedules.remove(newSchedule);
                    });
        });
        // Còn lại là các lịch mới cần thêm
        newSchedules.forEach((newSchedule) -> {
            newSchedule.setPrescription(original);
            original.getSchedules().add(newSchedule);
        });

        prescriptionRepository.save(original);
    }

    @Override
    public void deletePrescription(Long id) throws DataNotFoundException {
        Prescription prescription = prescriptionRepository.findByIdAndIsActiveIsTrue(id).orElseThrow(
                () -> new DataNotFoundException("Thông tin đơn thuốc không tồn tại.")
        );
        prescription.setActive(false);
        prescriptionRepository.save(prescription);
    }
}
