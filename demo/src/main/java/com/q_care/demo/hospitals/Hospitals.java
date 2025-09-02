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
    @Column(nullable = false, unique = true)
    private String name;
    // 위도
    @Column(nullable = false)
    private Double latitude;
    // 경도
    @Column(nullable = false)
    private Double longitude;
    // 주말휴진여부
    @Column(nullable = true)
    private Boolean holidayService = false;
    // 진료분야
    @ElementCollection(targetClass = MedicalSpecialty.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "hospital_specialty", joinColumns = @JoinColumn(name = "hospital_id"))
    @Column(name = "specialty")
    private Set<MedicalSpecialty> specialties = new HashSet<>();


    public enum MedicalSpecialty {
        /** 내과 */
        INTERNAL_MEDICINE,

        /** 가정의학과 */
        FAMILY_MEDICINE,

        /** 소아청소년과 */
        PEDIATRICS,

        /** 피부과 */
        DERMATOLOGY,

        /** 정신건강의학과 */
        PSYCHIATRY,

        /** 일반외과 */
        SURGERY,

        /** 정형외과 */
        ORTHOPEDIC_SURGERY,

        /** 신경외과 */
        NEUROSURGERY,

        /** 성형외과 */
        PLASTIC_SURGERY,

        /** 마취통증의학과 */
        ANESTHESIOLOGY,

        /** 재활의학과 */
        REHABILITATION,

        /** 안과 */
        OPHTHALMOLOGY,

        /** 이비인후과 */
        OTOLARYNGOLOGY,

        /** 산부인과 */
        OBSTETRICS_GYNECOLOGY,

        /** 비뇨의학과 (비뇨기과) */
        UROLOGY,

        /** 신경과 */
        NEUROLOGY
    }




}
