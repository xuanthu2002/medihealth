package com.mad.medihealth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "drug_users")
public class DrugUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @JsonIgnore
    @OneToMany(mappedBy = "drugUser", cascade = CascadeType.ALL)
    private List<Prescription> prescriptions;
}
