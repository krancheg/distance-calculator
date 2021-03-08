package com.example.DistanceCalculator.service;

import com.example.DistanceCalculator.model.Distance;
import java.util.List;

public interface DistanceService {

    void addDistanceList(List<Distance> listOfDistance);

    Distance getDistanceByCites(String fromCity, String toCity);

    void deleteByCites(String fromCity, String toCity);

}
