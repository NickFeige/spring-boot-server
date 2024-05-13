package com.example.demo.services;

import com.example.demo.models.Continent;

import java.util.List;
import java.util.Optional;

public interface ContinentService {
    List<Continent> findAllContinents();
    Optional<Continent> findContinentById(Integer id);
    Continent saveContinent(Continent continent);
    Continent updateContinent(Integer id, Continent continent);
    void deleteContinent(Integer id);
}