package com.mad.medihealth.repository;

import com.mad.medihealth.model.Prescription;
import com.mad.medihealth.model.PrescriptionStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrescriptionStatRepository extends JpaRepository<Prescription, Long> {

    @Query(value = "SELECT new com.mad.medihealth.model.PrescriptionStat(p.id, p.title, "
            + "(COUNT(CASE WHEN cn.isCheck = true THEN 1 ELSE NULL END)/COUNT(cn.id) * 100) as process) "
            + "FROM prescriptions p "
            + "JOIN p.drugUser du "
            + "JOIN p.schedules s "
            + "JOIN s.confirmNotifications cn "
            + "WHERE du.id = :duid "
            + "AND MONTH(cn.date) = MONTH(:dayofmonth) "
            + "GROUP BY p.id, p.title "
            + "ORDER BY process")
    List<PrescriptionStat> getPrescriptionStatByDrugUserIdAndMonth(Long duid, LocalDate dayofmonth);
}
