package com.mad.medihealth.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "prescription_items")
public class PrescriptionItem {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String name;
    private String note;
    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;
}
