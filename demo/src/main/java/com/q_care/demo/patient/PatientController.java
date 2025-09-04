package com.q_care.demo.patient;


import com.q_care.demo.MailService;
import com.q_care.demo.hospitals.HospitalRepository;
import com.q_care.demo.hospitals.Hospitals;
import com.q_care.demo.patient.entity.Appointment;
import com.q_care.demo.patient.entity.Patients;
import com.q_care.demo.patient.repository.AppointmentRepository;
import com.q_care.demo.patient.repository.PatientsRepository;
import com.q_care.demo.patient.service.PatientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PatientController {

    private final PatientsService patientsService;
    private final PatientsRepository patientsRepository;
    private final HospitalRepository hospitalRepository;
    private final AppointmentRepository appointmentRepository;
    private final MailService mailService;

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

    @GetMapping("/patient/appointment1")
    public String appointment1(Authentication auth, Model model) {
        String str = patientsService.getUserEmail(auth);
        Patients patient = patientsRepository.findByEmail(str).orElse(null);
        str = patient.getLanguage().toString();
        return "redirect:/patient/appointment?lang=" + str;
    }

    @GetMapping("/patient/appointment")
    public String appointment(Authentication auth, Model model) {
        String email = patientsService.getUserEmail(auth);
        List<Hospitals> hospitals = hospitalRepository.findAll();
        Patients patient = patientsRepository.findByEmail(email).orElse(null);
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("patient", patient);
        return "appointment.html";
    }
    @PostMapping("/patient/appointment")
    public String appointment(@RequestParam("hospitalId") Long hospitalId,
                              @RequestParam("date") LocalDate date,
                              @RequestParam("time") LocalTime time,
                              Authentication auth){
        Hospitals hospital = hospitalRepository.findById(hospitalId).orElse(null);
        Patients patient = patientsRepository.findByEmail(auth.getName()).orElse(null);
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(date);
        appointment.setAppointmentTime(time);
        appointment.setPatientId(patient.getPatientId());
        appointment.setHospitalId(hospital.getHospitalId());
        appointment.setDescription("(예시데이터) 머리가 욱신거리며(두통), 눈이 침침하고 집중력이 저하되는 증상이 나타납니다.");
        appointmentRepository.save(appointment);

        mailService.sendReservationMail(patient.getEmail(), patient.getFirstName() + patient.getLastName(), hospitalId, date, time);

        return "redirect:/patient";
    }


    @GetMapping("/patient/prescription-list1")
    public String prescriptionList1(Authentication auth, Model model) {
        String str = patientsService.getUserEmail(auth);
        Patients patient = patientsRepository.findByEmail(str).orElse(null);
        str = patient.getLanguage().toString();
        return "redirect:/patient/prescription-list?lang=" + str;
    }

    @GetMapping("/patient/prescription-list")
    public String prescriptionList(Authentication auth, Model model) {
        return "prescription.html";
    }

    @GetMapping("/patient/nearby-hospital1")
    public String nearbyHospital1(Authentication auth, Model model) {
        String str = patientsService.getUserEmail(auth);
        Patients patient = patientsRepository.findByEmail(str).orElse(null);
        str = patient.getLanguage().toString();
        return "redirect:/patient/nearby-hospital?lang=" + str;
    }

    @GetMapping("/patient/nearby-hospital")
    public String nearbyHospital(Authentication auth, Model model) {
        return "nearby-hospital.html";
    }



}
