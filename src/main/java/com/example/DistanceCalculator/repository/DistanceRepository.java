package com.example.DistanceCalculator.repository;

import com.example.DistanceCalculator.model.Distance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistanceRepository extends CrudRepository<Distance,Long> {

    Distance findFirstByFromCityAndToCity(String fromCity, String toCity);

}
