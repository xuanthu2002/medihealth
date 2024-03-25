package com.mad.medihealth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "prescription_items")
public class PrescriptionItem {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "note",nullable = false)
    private String note;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "prescription_id", nullable = false)
    private Prescription prescription;
}
