package com.example.petcarebook.controller;

import com.example.petcarebook.repository.PetRepository;
import com.example.petcarebook.repository.VaccinationRecordRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class HomeController {

    private final PetRepository petRepository;
    private final VaccinationRecordRepository vaccinationRecordRepository;

    public HomeController(PetRepository petRepository,
                          VaccinationRecordRepository vaccinationRecordRepository) {
        this.petRepository = petRepository;
        this.vaccinationRecordRepository = vaccinationRecordRepository;
    }

    @GetMapping("/")
    public String home(Model model) {

        long totalPets = petRepository.count();
        long totalVaccinations = vaccinationRecordRepository.count();

        LocalDate today = LocalDate.now();
        LocalDate sevenDaysLater = today.plusDays(7);
        long dueSoon = vaccinationRecordRepository
                .findByNextDueDateBetween(today, sevenDaysLater).size();

        model.addAttribute("totalPets", totalPets);
        model.addAttribute("totalVaccinations", totalVaccinations);
        model.addAttribute("dueSoon", dueSoon);

        return "index";
    }
}