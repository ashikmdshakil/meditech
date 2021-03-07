package com.quintet.meditech.repository;

import com.quintet.meditech.model.PrescriptionReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionReportJpaRepository extends JpaRepository<PrescriptionReport, Integer> {
}
