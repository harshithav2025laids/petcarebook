package com.example.petcarebook.repository;

import com.example.petcarebook.entity.VaccinationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VaccinationRecordRepository
        extends JpaRepository<VaccinationRecord, Long> {

    List<VaccinationRecord> findByNextDueDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );

    List<VaccinationRecord> findByPetIdOrderByDateGivenDesc(
            Long petId
    );
}