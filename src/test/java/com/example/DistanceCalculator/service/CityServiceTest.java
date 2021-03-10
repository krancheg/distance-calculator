package com.example.DistanceCalculator.service;

import com.example.DistanceCalculator.model.City;
import com.example.DistanceCalculator.model.response.ResultDistance;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CityServiceTest {

    private final CalculateDistance calculateDistance;
    private final CityService cityService;

    @Autowired
    public CityServiceTest(CalculateDistance calculateDistance, CityService cityService) {
        this.calculateDistance = calculateDistance;
        this.cityService = cityService;
    }

    @BeforeEach
    public void addTestCites(){
        List<City> listOfCity = new ArrayList<>();
        listOfCity.add(new City("TestCity1",46.35,48.04));
        listOfCity.add(new City("TestCity2",53.36,83.76));
        cityService.addCityList(listOfCity);
    }

    @Test
    public void getCityNotNull() {
        City city = cityService.getCityByName("TestCity1");
        Assert.notNull(city,"The City not found in database");
    }

    @Test
    public void getCityFromBase() {
        City city = cityService.getCityByName("TestCity1");
        Assert.isTrue(city.getLatitude()==46.35 && city.getLongitude()==48.04, "Geo in base not correct");
    }

    @Test
    public void ValidateStraightDistance() {
        List<String> city1 = new ArrayList<>();
        city1.add("TestCity1");
        List<String> city2 = new ArrayList<>();
        city2.add("TestCity2");
        List<ResultDistance> distance = calculateDistance.straightDistanceList(city1,city2);
        Assert.isTrue(distance.get(0).getDistance()==2652,"Distance not correct");
    }

    @AfterEach
    public void deleteTestCity() {
        cityService.deleteCity("TestCity1");
        cityService.deleteCity("TestCity2");
    }
}
