package com.quintet.meditech.repository;

import com.quintet.meditech.model.DoctorSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorSlotJpaRepo extends JpaRepository<DoctorSlot, Integer> {
    List<DoctorSlot> findByChamberId(int id);
}
