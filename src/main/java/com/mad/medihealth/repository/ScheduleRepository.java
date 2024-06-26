package com.mad.medihealth.repository;

import com.mad.medihealth.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query(value = "SELECT schedules.*\r\n"
            + "FROM schedules\r\n"
            + "JOIN prescriptions ON schedules.prescription_id = prescriptions.id\r\n"
            + "JOIN drug_users ON prescriptions.drug_user_id = drug_users.id\r\n"
            + "WHERE drug_users.user_id = :userId\r\n"
            + "  AND prescriptions.is_active = 1\r\n"
            + "  AND schedules.is_active = 1\r\n"
            + "  AND drug_users.is_active = 1\r\n"
            + "  AND NOT EXISTS (\r\n"
            + "    SELECT 1\r\n"
            + "    FROM confirm_notifications\r\n"
            + "    WHERE confirm_notifications.schedule_id = schedules.id\r\n"
            + "      AND confirm_notifications.date = curdate()\r\n"
            + ")\r\n"
            + "ORDER BY schedules.time;\r\n", nativeQuery = true)
    List<Schedule> getListScheduleofToday(@Param("userId") String userId);

    @Query(value = "SELECT schedules.*\r\n"
            + "FROM schedules\r\n"
            + "JOIN prescriptions ON schedules.prescription_id = prescriptions.id\r\n"
            + "JOIN drug_users ON prescriptions.drug_user_id = drug_users.id\r\n"
            + "WHERE prescriptions.is_active = 1\r\n"
            + "  AND schedules.is_active = 1\r\n"
            + "  AND drug_users.is_active = 1\r\n"
            + "  AND NOT EXISTS (\r\n"
            + "    SELECT 1\r\n"
            + "    FROM confirm_notifications\r\n"
            + "    WHERE confirm_notifications.schedule_id = schedules.id\r\n"
            + "      AND confirm_notifications.date = DATE_SUB(CURDATE(), INTERVAL 1 DAY)\r\n"
            + ")\r\n"
            + "ORDER BY schedules.time;\r\n", nativeQuery = true)
    List<Schedule> getListScheduletoDefaultConfirm();

    Iterable<Schedule> findAllByPrescriptionIdAndIsActiveIsTrueOrderByTimeAsc(Long pid);

    Iterable<Schedule> findAllByPrescriptionDrugUserUserIdOrderByTimeAsc(String userId);

}