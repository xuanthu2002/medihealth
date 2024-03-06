package com.mad.medihealth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Entity(name = "schedules")
public class Schedule {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private LocalTime time;
    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;
    @OneToMany(mappedBy = "schedule")
    private List<ConfirmNotification> listCN;
}
