package com.example.DistanceCalculator.repository;

import com.example.DistanceCalculator.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City,Long> {

    City findFirstByName(String name);

}
