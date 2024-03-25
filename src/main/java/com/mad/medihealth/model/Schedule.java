package com.mad.medihealth.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "prescription_id", nullable = false)
    private Prescription prescription;

    @OneToMany(mappedBy = "schedule")
    private List<ConfirmNotification> listCN;

    @Column(name = "is_active")
    boolean isActive;
}
