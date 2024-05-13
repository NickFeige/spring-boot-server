package com.example.demo.services.impl;

import com.example.demo.models.Continent;
import com.example.demo.repositories.ContinentRepository;
import com.example.demo.services.ContinentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContinentServiceImpl implements ContinentService {

    @Autowired
    private ContinentRepository continentRepository;

    @Override
    public List<Continent> findAllContinents() {
        return continentRepository.findAll();
    }

    @Override
    public Optional<Continent> findContinentById(Integer id) {
        return continentRepository.findById(id);
    }

    @Override
    public Continent saveContinent(Continent continent) {
        return continentRepository.save(continent);
    }

    @Override
    public Continent updateContinent(Integer id, Continent continent) {
        return continentRepository.findById(id)
                .map(existingContinent -> {
                    existingContinent.setContinent(continent.getContinent());
                    existingContinent.setArea(continent.getArea());
                    existingContinent.setPop(continent.getPop());
                    return continentRepository.save(existingContinent);
                }).orElseThrow(() -> new RuntimeException("Continent not found with id " + id));
    }

    @Override
    public void deleteContinent(Integer id) {
        Continent continent = continentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Continent not found with id " + id));
        continentRepository.delete(continent);
    }
}