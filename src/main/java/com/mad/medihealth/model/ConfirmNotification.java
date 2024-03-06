package com.mad.medihealth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity(name = "confirm_notifications")
public class ConfirmNotification {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @Column(name = "is_check")
    private boolean isCheck;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
}
