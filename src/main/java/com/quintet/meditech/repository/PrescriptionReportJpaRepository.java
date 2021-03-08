package com.quintet.meditech.repository;

import com.quintet.meditech.model.PrescriptionReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionReportJpaRepository extends JpaRepository<PrescriptionReport, Integer> {
    List<PrescriptionReport> findByAppoinmentId(int id);
}
