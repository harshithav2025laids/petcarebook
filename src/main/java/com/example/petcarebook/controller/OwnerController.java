package com.example.petcarebook.controller;

import com.example.petcarebook.entity.Owner;
import com.example.petcarebook.repository.OwnerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerRepository ownerRepository;

    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @GetMapping("/new")
    public String showOwnerForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "add-owner";
    }

    @PostMapping("/save")
    public String saveOwner(@ModelAttribute Owner owner) {

        ownerRepository.save(owner);

        return "redirect:/";
    }
}