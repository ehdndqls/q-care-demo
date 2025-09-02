package com.q_care.demo.hospitals;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HospitalController {

    @GetMapping("/hospital")
    public String hospital() {
        return "hospital.html";
    }
}
