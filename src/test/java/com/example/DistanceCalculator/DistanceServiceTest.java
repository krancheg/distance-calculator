package com.example.DistanceCalculator;

import com.example.DistanceCalculator.model.Distance;
import com.example.DistanceCalculator.service.CalculateDistance;
import com.example.DistanceCalculator.service.DistanceService;
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

    @Autowired
    public DistanceServiceTest(CalculateDistance calculateDistance, DistanceService distanceService) {
        this.calculateDistance = calculateDistance;
        this.distanceService = distanceService;
    }

    @BeforeEach
    public void addTestDistances() {
        List<Distance> distances = new ArrayList<>();
        distances.add(new Distance("TestDistance1","TestDistance2",2346));
        distanceService.addDistanceList(distances);
    }

    @Test
    public void getDistanceNotNull() {
        Distance distance = distanceService.getDistanceByCites("TestDistance1","TestDistance2");
        Assert.notNull(distance,"The Distance not found in database");
    }

    @Test
    public void getCityFromBase() {
        Distance distance = distanceService.getDistanceByCites("TestDistance1","TestDistance2");
        Assert.isTrue(distance.getDistance()==2346,"Distance in base not correct");
    }

    @Test
    public void ValidateMatrixDistance() {
        long distance = calculateDistance.matrixDistance("TestDistance1","TestDistance2");
        Assert.isTrue(distance==2346, "Distance not correct");
    }

    @AfterEach
    public void deleteTestDistances() {
        distanceService.deleteByCites("TestDistance1","TestDistance2");
    }
}
