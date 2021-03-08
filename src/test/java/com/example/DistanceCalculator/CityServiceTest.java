package com.example.DistanceCalculator;

import com.example.DistanceCalculator.model.City;
import com.example.DistanceCalculator.service.CalculateDistance;
import com.example.DistanceCalculator.service.CityService;
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
        long distance = calculateDistance.straightDistance("TestCity1","TestCity2");
        Assert.isTrue(distance==2652,"Distance not correct");
    }

    @AfterEach
    public void deleteTestCity() {
        cityService.deleteCity("TestCity1");
        cityService.deleteCity("TestCity2");
    }


}
