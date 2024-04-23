package com.mad.medihealth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DrugUser drugUser;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<PrescriptionItem> prescriptionItems;

    @JsonIgnoreProperties("prescription")
    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<Schedule> schedules;
}
