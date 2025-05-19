package com.monTour.guidevoyage.controller;

import com.monTour.guidevoyage.dto.CircuitRequest;
import com.monTour.guidevoyage.dto.LocationRequest;
import com.monTour.guidevoyage.service.TourismeApiService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tourisme")
public class TourismeController {

    private final TourismeApiService tourismeApiService;

    public TourismeController(TourismeApiService tourismeApiService) {
        this.tourismeApiService = tourismeApiService;
    }

    @GetMapping("/")
    public String index() {
        return "tourisme";
    }

    @PostMapping("/recommend")
    @ResponseBody
    public ResponseEntity<Object> recommendLocations(@Valid @RequestBody LocationRequest request) {
        return ResponseEntity.ok(tourismeApiService.recommendLocations(request));
    }

    @PostMapping("/recommend-programmes")
    @ResponseBody
    public ResponseEntity<Object> generateCircuit(@Valid @RequestBody CircuitRequest request) {
        return ResponseEntity.ok(tourismeApiService.generateCircuit(request));
    }
}
