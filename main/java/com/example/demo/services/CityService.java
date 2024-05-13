package com.example.demo.services;
import com.example.demo.models.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    List<City> findAllCities();
    Optional<City> findCityById(Integer id);
    City saveCity(City city);
    City updateCity(Integer id, City city);
    void deleteCity(Integer id);
}