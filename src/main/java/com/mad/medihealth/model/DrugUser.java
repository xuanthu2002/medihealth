package com.mad.medihealth.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "drug_users")
public class DrugUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "user_id")
    private String userId;
}
