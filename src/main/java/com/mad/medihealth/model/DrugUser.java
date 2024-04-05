package com.mad.medihealth.model;

import jakarta.persistence.*;
import lombok.Data;

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

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}
