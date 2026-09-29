package com.example.petcarebook.controller;

import com.example.petcarebook.entity.VaccinationRecord;
import com.example.petcarebook.repository.PetRepository;
import com.example.petcarebook.repository.VaccinationRecordRepository;
import com.example.petcarebook.repository.VaccinationTypeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/vaccinations")
public class VaccinationController {

    private final VaccinationRecordRepository vaccinationRecordRepository;
    private final PetRepository petRepository;
    private final VaccinationTypeRepository vaccinationTypeRepository;

    public VaccinationController(VaccinationRecordRepository vaccinationRecordRepository,
                                 PetRepository petRepository,
                                 VaccinationTypeRepository vaccinationTypeRepository) {
        this.vaccinationRecordRepository = vaccinationRecordRepository;
        this.petRepository = petRepository;
        this.vaccinationTypeRepository = vaccinationTypeRepository;
    }

    // Show add vaccination form
    @GetMapping("/new")
    public String showVaccinationForm(Model model) {
        model.addAttribute("pets", petRepository.findAll());
        model.addAttribute("vaccinationTypes", vaccinationTypeRepository.findAll());
        return "add-vaccination";
    }

    // Save a vaccination record
    @PostMapping("/save")
    public String saveVaccination(@RequestParam Long petId,
                                  @RequestParam Long vaccinationTypeId,
                                  @RequestParam String dateGiven,
                                  @RequestParam String nextDueDate) {

        VaccinationRecord record = new VaccinationRecord();
        record.setPet(petRepository.findById(petId).orElseThrow());
        record.setVaccinationType(vaccinationTypeRepository.findById(vaccinationTypeId).orElseThrow());
        record.setDateGiven(LocalDate.parse(dateGiven));
        record.setNextDueDate(LocalDate.parse(nextDueDate));

        vaccinationRecordRepository.save(record);

        return "redirect:/";
    }

    // Show upcoming vaccinations (due within 7 days)
    @GetMapping("/upcoming")
    public String upcomingVaccinations(Model model) {
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysLater = today.plusDays(7);
        List<VaccinationRecord> records =
                vaccinationRecordRepository.findByNextDueDateBetween(today, sevenDaysLater);
        model.addAttribute("records", records);
        return "upcoming-vaccinations";
    }

    // Show vaccination history for a selected pet
    @GetMapping("/history")
    public String vaccinationHistory(@RequestParam(required = false) Long petId, Model model) {
        model.addAttribute("pets", petRepository.findAll());
        model.addAttribute("selectedPetId", petId);

        if (petId != null) {
            List<VaccinationRecord> records =
                    vaccinationRecordRepository.findByPetIdOrderByDateGivenDesc(petId);
            model.addAttribute("records", records);
        } else {
            model.addAttribute("records", Collections.emptyList());
        }

        return "vaccination-history";
    }
}
