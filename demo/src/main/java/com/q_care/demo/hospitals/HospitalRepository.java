package com.q_care.demo.hospitals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospitals, Long> {


    List<Hospitals> findAll();
}
