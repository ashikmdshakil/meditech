package com.quintet.meditech.repository;

import com.quintet.meditech.model.Appoinment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppoinmentJpaRepository extends JpaRepository<Appoinment,Integer> {
    public List<Appoinment> findByUserMobileNumber(String number);
    public List<Appoinment> findByDoctorSlotId(int id);
    int countAppoinmentsByDoctorSlotId(int id);
    boolean existsByUserUserId(int id);
}
