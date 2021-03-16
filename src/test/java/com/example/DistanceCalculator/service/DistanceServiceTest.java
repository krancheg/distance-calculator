package com.example.DistanceCalculator.service;

import com.example.DistanceCalculator.model.Distance;
import com.example.DistanceCalculator.model.response.ResultDistance;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DistanceServiceTest {

    private final CalculateDistance calculateDistance;
    private final DistanceService distanceService;
    private final CityService cityService;

    @Autowired
    public DistanceServiceTest(CalculateDistance calculateDistance, DistanceService distanceService, CityService cityService) {
        this.calculateDistance = calculateDistance;
        this.distanceService = distanceService;
        this.cityService = cityService;
    }

    @BeforeEach
    public void addTestDistances() {
        List<Distance> distances = new ArrayList<>();
        distances.add(new Distance("TestCity1","TestCity2",2346));
        distanceService.addDistanceList(distances);
    }

    @Test
    public void getDistanceNotNull() {
        Distance distance = distanceService.getDistanceByCites("null","TestCity2");
        Assert.notNull(distance,"Return null in distanceService.getDistanceByCites");
    }

    @Test
    public void getCityFromBase() {
        Distance distance = distanceService.getDistanceByCites("TestCity1","TestCity2");
        Assert.isTrue(distance.getDistance()==2346,"Distance in base not correct");
    }

    @Test
    public void ValidateMatrixDistance() {
        List<String> city1 = new ArrayList<>();
        city1.add("TestCity1");
        List<String> city2 = new ArrayList<>();
        city2.add("TestCity2");
        List<ResultDistance> distance = calculateDistance.matrixDistanceList(city1,city2);
        Assert.isTrue(distance.get(0).getDistance()==2346, "Distance not correct");

    }

    @AfterEach
    public void deleteTestDistances() {
        distanceService.deleteByCites("TestCity1","TestCity2");
    }
}
