package com.mad.medihealth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "prescriptions")
public class Prescription {
    @Id
    private @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String title;
    private boolean isActive;
    @ManyToOne
    @JoinColumn(name = "drug_user_id")
    private DrugUser drugUser;
    @OneToMany(mappedBy = "prescription")
    private List<PrescriptionItem> prescriptionItems;
    @OneToMany(mappedBy = "prescription")
    private List<Schedule> schedules;
}