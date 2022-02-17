package com.example.springbootupandrunning.controller;

import com.example.springbootupandrunning.domain.Aircraft;
import com.example.springbootupandrunning.repository.AircraftRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Controller
public class PositionController {
    @NonNull
    private final AircraftRepository aircraftRepository;
    private WebClient webClient = WebClient.create("http://localhost:7634/aircraft");

    @GetMapping("/aircraft")
    public String getCurrentAircraftPositions(Model model) {
        aircraftRepository.deleteAll();

        webClient
            .get()
            .retrieve()
            .bodyToFlux(Aircraft.class)
            .filter(plane -> !plane.getReg().isEmpty())
            .toStream()
            .forEach(aircraftRepository::save);

        model.addAttribute("currentPositions", aircraftRepository.findAll());

        return "positions";
    }
}
