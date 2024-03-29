package com.mad.medihealth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity(name = "prescriptions")
public class Prescription {
    @Id
    private @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
    
    @ManyToOne
    @JoinColumn(name = "drug_user_id", nullable = false)
    private DrugUser drugUser;
    
    @OneToMany(mappedBy = "prescription")
    private List<PrescriptionItem> prescriptionItems;
    
    @OneToMany(mappedBy = "prescription")
    @JsonIgnoreProperties("prescription")
    private List<Schedule> schedules;
}
