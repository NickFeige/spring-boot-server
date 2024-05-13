package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Continent;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, Integer> {
}