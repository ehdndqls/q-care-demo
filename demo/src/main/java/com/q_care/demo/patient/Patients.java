package com.q_care.demo.patient;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Patients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    // 로그인할때 사용할 email
    @Column(nullable = false, unique = true)
    private String email;
    // 생년월일
    @Column(nullable = false)
    private String brithDate;
    // 성
    @Column(nullable = false)
    private String firstName;
    // 이름
    @Column(nullable = false)
    private String lastName;
    // 연락처
    @Column(nullable = true)
    private String phoneNum;
    // 위도
    @Column(nullable = false)
    private Double latitude;
    // 경도
    @Column(nullable = false)
    private Double longitude;
    // 언어
    @Enumerated(EnumType.STRING) // Enum을 문자열로 저장
    @Column(nullable = false)
    private LanguageType language;

    public enum LanguageType {
        KOREAN,       // 한국어
        ENGLISH,      // 영어
        JAPANESE,     // 일본어
        CHINESE,      // 중국어
        FRENCH,       // 프랑스어
        GERMAN,       // 독일어
        SPANISH,      // 스페인어
        RUSSIAN,      // 러시아어
        ARABIC,       // 아랍어
        HINDI,        // 힌디어
        PORTUGUESE,   // 포르투갈어
        ITALIAN,      // 이탈리아어
        VIETNAMESE,   // 베트남어
        THAI,         // 태국어
        TURKISH,      // 터키어
    }



}
