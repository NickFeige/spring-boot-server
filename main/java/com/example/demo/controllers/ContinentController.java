package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Continent;
import com.example.demo.services.ContinentService;

import java.util.List;

@RestController
@RequestMapping("/api/continents")
public class ContinentController {

    @Autowired
    private ContinentService continentService;

    // Get all continents
    @GetMapping
    public List<Continent> getAllContinents() {
        return continentService.findAllContinents();
    }

    // Get a single continent by ID
    @GetMapping("/{id}")
    public ResponseEntity<Continent> getContinentById(@PathVariable Integer id) {
        return continentService.findContinentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new continent
    @PostMapping
    public ResponseEntity<Continent> createContinent(@RequestBody Continent continent) {
        Continent savedContinent = continentService.saveContinent(continent);
        return ResponseEntity.ok(savedContinent);
    }

    // Update a continent
    @PutMapping("/{id}")
    public ResponseEntity<Continent> updateContinent(@PathVariable Integer id, @RequestBody Continent continentDetails) {
        try {
            Continent updatedContinent = continentService.updateContinent(id, continentDetails);
            return ResponseEntity.ok(updatedContinent);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a continent
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContinent(@PathVariable Integer id) {
        try {
            continentService.deleteContinent(id);
            return ResponseEntity.ok().<Void>build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}