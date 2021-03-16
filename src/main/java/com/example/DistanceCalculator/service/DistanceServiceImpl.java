package com.example.DistanceCalculator.service;

import com.example.DistanceCalculator.model.Distance;
import com.example.DistanceCalculator.repository.DistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistanceServiceImpl implements DistanceService {

    private DistanceRepository distanceRepository;

    @Autowired
    public DistanceServiceImpl(DistanceRepository distanceRepository) {
        this.distanceRepository = distanceRepository;
    }

    @Override
    public void addDistanceList(List<Distance> listOfDistance) {
        if (listOfDistance.isEmpty())
            return;
        distanceRepository.saveAll(listOfDistance);
    }

    @Override
    public Distance getDistanceByCites(String fromCity, String toCity) {
        Distance distance = distanceRepository.findFirstByFromCityAndToCity(fromCity, toCity);
        return (distance == null) ? new Distance() : distance;
    }

    @Override
    public void deleteByCites(String fromCity, String toCity) {
        Distance distance = distanceRepository.findFirstByFromCityAndToCity(fromCity, toCity);
        if (distance==null)
            return;
        distanceRepository.deleteById(distance.getId());
    }

}
