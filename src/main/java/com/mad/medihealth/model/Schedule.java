package com.mad.medihealth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;


@Data
@Entity(name = "schedules")
public class Schedule {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    
    @Column(name = "time", nullable = false)
    private LocalTime time;
    
    @Column(name = "is_active", nullable = false)
    private boolean isActive;
    
    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;
    
    @OneToMany(mappedBy = "schedule")
    private List<ConfirmNotification> confirmNotifications;
}