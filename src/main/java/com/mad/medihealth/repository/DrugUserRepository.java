package com.mad.medihealth.repository;

import com.mad.medihealth.model.DrugUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugUserRepository extends JpaRepository<DrugUser, Long> {
    Iterable<DrugUser> findAllByUserId(String uid);
}