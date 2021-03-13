package com.quintet.meditech.repository;

import com.quintet.meditech.model.MedicineScedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface MedicineSceduleJpaRepository extends JpaRepository<MedicineScedule, Integer> {
    void deleteAllByPrescriptionId(int id);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM meditech.medicine_scedule where prescription_id is null;", nativeQuery = true)
    void deleteAllNull();
}
