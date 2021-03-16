package com.quintet.meditech.repository;

import com.quintet.meditech.model.DoctorSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DoctorSlotJpaRepo extends JpaRepository<DoctorSlot, Integer> {
    List<DoctorSlot> findByChamberId(int id);
    DoctorSlot findById(int id);
    @Query(value = "SELECT * FROM doctor_slot WHERE start_time LIKE :date% AND chamber_id = :chamberId ;", nativeQuery = true)
    List<DoctorSlot> findByTime(String date,int chamberId);
}
