package com.q_care.demo.patient;

import com.q_care.demo.user.CustomUserDetails;
import com.q_care.demo.user.User;
import com.q_care.demo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PatientsService {

    private final PatientsRepository patientsRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public void savePatient(PatientsForm patientsForm) {
        // 이메일 중복 체크
        if (patientsRepository.findByEmail(patientsForm.getEmail()).isPresent()) {
            throw new IllegalArgumentException("already exists");
        }

        // Patient 저장
        Patients patient = new Patients();
        patient.setEmail(patientsForm.getEmail());
        patient.setFirstName(patientsForm.getFirstName());
        patient.setLastName(patientsForm.getLastName());
        patient.setBrithDate(patientsForm.getBrithDate());
        patient.setPhoneNum(patientsForm.getPhoneNum());
        patient.setLatitude(patientsForm.getLatitude());
        patient.setLongitude(patientsForm.getLongitude());
        patient.setLanguage(patientsForm.getLanguage());
        patientsRepository.save(patient);

        // User 저장
        User user = new User();
        user.setUsername(patient.getEmail());
        user.setPassword(passwordEncoder.encode(patientsForm.getPassword()));
        user.setRole(User.Role.PATIENT);
        user.setRefId(patient.getPatientId());
        userRepository.save(user);
    }

    public String getLang(String username){
        Patients patient = patientsRepository.findByEmail(username).orElse(null);

        return patient.getLanguage().toString();
    }

    public String getUserEmail(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
            throw new IllegalStateException("유효하지 않은 인증 정보입니다.");
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }
}
