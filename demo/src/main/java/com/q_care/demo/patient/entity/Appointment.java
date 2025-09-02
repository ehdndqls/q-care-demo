package com.q_care.demo.patient.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@ToString
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long AppointmentId;

    private Long patientId;
    private Long hospitalId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String description;


}
