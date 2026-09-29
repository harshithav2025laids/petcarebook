package com.example.petcarebook.repository;

import com.example.petcarebook.entity.VaccinationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationTypeRepository
        extends JpaRepository<VaccinationType, Long> {

}