package com.q_care.demo.hospitals;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class Hospitals {
    // 기본키: 병원ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hospitalId;
    // 병원명
    @Column(nullable = false)
    private String name;
    // 위도
    @Column(nullable = false)
    private Double latitude;
    // 경도
    @Column(nullable = false)
    private Double longitude;
    // 주말휴진여부
    @Column(nullable = true)
    private Boolean holidayService;
    // 진료분야
    @ElementCollection(targetClass = MedicalSpecialty.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "hospital_specialty", joinColumns = @JoinColumn(name = "hospital_id"))
    @Column(name = "specialty")
    private Set<MedicalSpecialty> specialties = new HashSet<>();


    public enum MedicalSpecialty {
        INTERNAL_MEDICINE,
        FAMILY_MEDICINE,
        PEDIATRICS,
        DERMATOLOGY,
        PSYCHIATRY,
        SURGERY,
        ORTHOPEDIC_SURGERY,
        NEUROSURGERY,
        PLASTIC_SURGERY,
        ANESTHESIOLOGY,
        REHABILITATION,
        OPHTHALMOLOGY,
        OTOLARYNGOLOGY,
        OBSTETRICS_GYNECOLOGY,
        UROLOGY,
        NEUROLOGY
    }




}
