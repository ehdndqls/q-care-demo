package com.q_care.demo.patient;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PatientController {

    @GetMapping("/patient")
    public String patients() {
        return "patient-main.html";
    }

}
