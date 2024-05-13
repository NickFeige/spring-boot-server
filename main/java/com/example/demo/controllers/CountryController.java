package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Country;
import com.example.demo.services.CountryService;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    // Get all countries
    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.findAllCountries();
    }

    // Get a single country by ID
    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Integer id) {
        return countryService.findCountryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new country
    @PostMapping
    public Country createCountry(@RequestBody Country country) {
        return countryService.saveCountry(country);
    }

    // Update a country
    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable Integer id, @RequestBody Country countryDetails) {
        try {
            Country updatedCountry = countryService.updateCountry(id, countryDetails);
            return ResponseEntity.ok(updatedCountry);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a country
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Integer id) {
        try {
            countryService.deleteCountry(id);
            return ResponseEntity.ok().<Void>build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}