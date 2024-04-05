package com.mad.medihealth.repository;

import com.mad.medihealth.model.ConfirmNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConfirmNotificationRepository extends JpaRepository<ConfirmNotification, Long> {
    List<ConfirmNotification> findConfirmNotificationByScheduleIdAndDate(Long scheduleId, LocalDate date);

    @Query(value = "SELECT * FROM confirm_notifications \r\n"
            + "WHERE schedule_id = :scheduleId \r\n"
            + "AND date <= :end AND date >= :start \r\n"
            + "ORDER BY date", nativeQuery = true)
    List<ConfirmNotification> getConfirmNotificationByScheduldeIdAndWeek(Long scheduleId, LocalDate start, LocalDate end);
}
