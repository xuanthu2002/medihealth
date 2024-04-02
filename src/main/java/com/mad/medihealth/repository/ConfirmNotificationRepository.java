package com.mad.medihealth.repository;

import com.mad.medihealth.model.ConfirmNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmNotificationRepository extends JpaRepository<ConfirmNotification, Long> {

}
