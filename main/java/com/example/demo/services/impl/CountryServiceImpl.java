package com.example.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.Country;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.services.CountryService;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Country> findAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findCountryById(Integer id) {
        return countryRepository.findById(id);
    }

    @Override
    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country updateCountry(Integer id, Country countryDetails) {
        return countryRepository.findById(id).map(country -> {
            country.setCountry(countryDetails.getCountry());
            country.setHeadOfState(countryDetails.getHeadOfState());
            country.setContinent(countryDetails.getContinent());
            return countryRepository.save(country);
        }).orElseThrow(() -> new RuntimeException("Country not found with id " + id));
    }

    @Override
    public void deleteCountry(Integer id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found with id " + id));
        countryRepository.delete(country);
    }
}