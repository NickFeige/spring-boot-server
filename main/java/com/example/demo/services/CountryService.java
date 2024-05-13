package com.example.demo.services;

import com.example.demo.models.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAllCountries();
    Optional<Country> findCountryById(Integer id);
    Country saveCountry(Country country);
    Country updateCountry(Integer id, Country country);
    void deleteCountry(Integer id);
}