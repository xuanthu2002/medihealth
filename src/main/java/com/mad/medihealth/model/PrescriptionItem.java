package com.mad.medihealth.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;

@Data
@Entity(name = "prescription_items")
public class PrescriptionItem {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "note", nullable = false)
    private String note;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "prescription_id", nullable = false)
    private Prescription prescription;
}
