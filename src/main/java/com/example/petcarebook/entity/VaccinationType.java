package com.example.petcarebook.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vaccination_types")
public class VaccinationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String vaccineName;

    @Column(nullable = false)
    private int intervalDays;

    @OneToMany(mappedBy = "vaccinationType")
    private List<VaccinationRecord> vaccinationRecords = new ArrayList<>();

    public VaccinationType() {
    }

    public VaccinationType(String vaccineName, int intervalDays) {
        this.vaccineName = vaccineName;
        this.intervalDays = intervalDays;
    }

    public Long getId() {
        return id;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public int getIntervalDays() {
        return intervalDays;
    }

    public void setIntervalDays(int intervalDays) {
        this.intervalDays = intervalDays;
    }

    public List<VaccinationRecord> getVaccinationRecords() {
        return vaccinationRecords;
    }

    public void setVaccinationRecords(
            List<VaccinationRecord> vaccinationRecords) {
        this.vaccinationRecords = vaccinationRecords;
    }
}