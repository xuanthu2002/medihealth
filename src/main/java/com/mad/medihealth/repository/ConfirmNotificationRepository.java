package com.mad.medihealth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mad.medihealth.model.ConfirmNotification;

@Repository
public interface ConfirmNotificationRepository extends JpaRepository<ConfirmNotification, Long>{

}
