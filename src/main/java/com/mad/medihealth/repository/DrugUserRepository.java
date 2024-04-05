package com.mad.medihealth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.mad.medihealth.model.DrugUser;

@Service
public interface DrugUserRepository extends JpaRepository<DrugUser, Long>{
	Iterable<DrugUser> findAllByUserId(String uid);
}
