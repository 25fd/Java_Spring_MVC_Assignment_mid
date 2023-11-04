package com.humber.falgunmidassignment.controllers;

import com.humber.falgunmidassignment.models.Pet;
import com.humber.falgunmidassignment.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PetController implements EnvironmentAware {
    Environment environment;

    private PetRepository petRepository;

    @Autowired
    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        List<Pet> pets = (List<Pet>) petRepository.findAll();
        model.addAttribute("pets", pets);
        model.addAttribute("appName", environment.getProperty("application.name"));
        return "home";
    }

    @PostMapping("/add")
    public String addPet(Model model, Pet pet) {
        System.out.println(pet);
        petRepository.save(pet);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addPetPage(Model model) {
        model.addAttribute("pet", new Pet());
        model.addAttribute("appName", environment.getProperty("application.name"));
        return "Add";
    }

    @PostMapping("/delete/{id}")
    public String deletePet(Model model, @PathVariable int id) {
        petRepository.deleteById(id);
        return "redirect:/";
    }
}
