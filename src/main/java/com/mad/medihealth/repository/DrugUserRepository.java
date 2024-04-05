package com.mad.medihealth.repository;

import com.mad.medihealth.model.DrugUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DrugUserRepository extends JpaRepository<DrugUser, Long> {
    Iterable<DrugUser> findAllByUserIdAndIsActiveIsTrue(String uid);

    boolean existsByIdAndIsActiveIsTrue(Long id);

    Optional<DrugUser> findByIdAndIsActiveIsTrue(Long id);
}