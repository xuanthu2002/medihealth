package com.mad.medihealth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity(name = "schedules")
public class Schedule {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    
    @Column(name = "time", nullable = false)
    private LocalTime time;
    
    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;
}
