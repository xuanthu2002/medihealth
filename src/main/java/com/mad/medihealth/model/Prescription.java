package com.mad.medihealth.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<PrescriptionItem> prescriptionItems;
    @JsonManagedReference
    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<Schedule> schedules;
}
