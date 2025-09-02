package com.q_care.demo.patient;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PatientController {

    private final PatientsService patientsService;
    private final PatientsRepository patientsRepository;

    @GetMapping("/patient")
    public String patients(Authentication auth) {
        String lang = patientsService.getLang(auth.getName());
        return "redirect:/patient-dashboard?lang=" + lang;
    }

    @GetMapping("/patient-dashboard")
    public String patientDashboard(Authentication auth, Model model) {
        String email = patientsService.getUserEmail(auth);
        Patients patient = patientsRepository.findByEmail(email).orElse(null);
        model.addAttribute("patient", patient);
        return "patient-main.html";
    }

    @GetMapping("/patient/symptom")
    public String symptoms(Authentication auth) {
        String lang = patientsService.getLang(auth.getName());
        return "redirect:/patient/symptom-dashboard?lang=" + lang;
    }

    @GetMapping("/patient/symptom-dashboard")
    public String symptomDashboard() {
        return "search-symptom.html";
    }


}
