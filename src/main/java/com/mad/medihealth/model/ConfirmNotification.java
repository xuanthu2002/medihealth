package com.mad.medihealth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity(name = "confirm_notifications")
public class ConfirmNotification {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    
    @Column(name = "is_check", nullable = false)
    private boolean isCheck;
    
    @Column(name = "date", nullable = false)
    private LocalDate date;
    
    @Column(name = "time", nullable = false)
    private LocalTime time;
    
    @Column(name = "des", nullable = true)
    private String des;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "prescription_id", nullable = false )
    private Prescription prescription;
}
