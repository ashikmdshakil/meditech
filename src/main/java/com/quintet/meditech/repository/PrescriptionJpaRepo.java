package com.quintet.meditech.repository;

import com.quintet.meditech.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionJpaRepo extends JpaRepository<Prescription, Integer> {
    List<Prescription> findByUserUserId(int id);
}
