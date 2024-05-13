package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.City;
import com.example.demo.services.CityService;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200") 
@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    // Get all cities
    @GetMapping
    public List<City> getAllCities() {
        return cityService.findAllCities();
    }

    // Get a single city by ID
    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Integer id) {
        return cityService.findCityById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new city
    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City city) {
        City savedCity = cityService.saveCity(city);
        return ResponseEntity.ok(savedCity);
    }

    // Update an existing city
    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Integer id, @RequestBody City cityDetails) {
        try {
            City updatedCity = cityService.updateCity(id, cityDetails);
            return ResponseEntity.ok(updatedCity);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a city
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Integer id) {
        try {
            cityService.deleteCity(id);
            return ResponseEntity.ok().<Void>build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}