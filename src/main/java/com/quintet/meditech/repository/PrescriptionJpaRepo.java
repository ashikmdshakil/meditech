package com.quintet.meditech.repository;

import com.quintet.meditech.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface PrescriptionJpaRepo extends JpaRepository<Prescription, Integer> {
    List<Prescription> findByPatientUserId(int id);
    Prescription findByAppoinmentIdAndPatientUserId(int aId, int uId);
    Prescription findFirstByAppoinmentId(int aId);
    void deleteAllByAppoinmentId(int aid);
}
