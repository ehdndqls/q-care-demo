package com.q_care.demo.patient.repository;

import com.q_care.demo.patient.entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientsRepository extends JpaRepository<Patients, Long> {
    Optional<Patients> findByEmail(String email);
}
