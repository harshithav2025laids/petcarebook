package com.example.petcarebook.controller;

import com.example.petcarebook.entity.Pet;
import com.example.petcarebook.repository.OwnerRepository;
import com.example.petcarebook.repository.PetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pets")
public class PetController {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    public PetController(PetRepository petRepository,
                         OwnerRepository ownerRepository) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
    }

    @GetMapping("/new")
    public String showPetForm(Model model) {

        model.addAttribute("pet", new Pet());
        model.addAttribute("owners", ownerRepository.findAll());

        return "add-pet";
    }

    @PostMapping("/save")
    public String savePet(@ModelAttribute Pet pet,
                          @RequestParam Long ownerId) {

        pet.setOwner(ownerRepository.findById(ownerId).orElseThrow());

        petRepository.save(pet);

        return "redirect:/";
    }

    @GetMapping
    public String listPets(Model model) {

        model.addAttribute("pets", petRepository.findAll());

        return "pets";
    }
}