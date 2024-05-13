package com.example.demo.services.impl;

import com.example.demo.models.City;
import com.example.demo.repositories.CityRepository;
import com.example.demo.services.CityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> findAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> findCityById(Integer id) {
        return cityRepository.findById(id);
    }

    @Override
    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City updateCity(Integer id, City cityDetails) {
        return cityRepository.findById(id).map(existingCity -> {
            existingCity.setCity(cityDetails.getCity());
            existingCity.setIsCapital(cityDetails.getIsCapital());
            existingCity.setPopulation(cityDetails.getPopulation());
            existingCity.setCountry(cityDetails.getCountry());
            return cityRepository.save(existingCity);
        }).orElseThrow(() -> new RuntimeException("City not found with id " + id));
    }

    @Override
    public void deleteCity(Integer id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found with id " + id));
        cityRepository.delete(city);
    }
}