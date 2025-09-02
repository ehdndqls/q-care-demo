package com.q_care.demo.patient.dto;

import com.q_care.demo.patient.entity.Patients;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PatientsForm {

    private String Email;
    private String password;
    private String confirmPassword;
    private String brithDate;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private Double latitude;
    private Double longitude;
    private Patients.LanguageType language;

    // 기본 생성자
    public PatientsForm() {
    }

    public PatientsForm(String Email,
                        String password,
                        String confirmPassword,
                        String brithDate,
                        String firstName,
                        String lastName,
                        String phoneNum,
                        Patients.LanguageType lang){
        this.Email = Email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.brithDate = brithDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.language = lang;
        this.latitude = 36.774;
        this.longitude = 126.933;
    }
}
