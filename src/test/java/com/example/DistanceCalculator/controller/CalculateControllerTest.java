package com.example.DistanceCalculator.controller;

import com.example.DistanceCalculator.model.City;
import com.example.DistanceCalculator.model.Distance;
import com.example.DistanceCalculator.model.request.CalculateParams;
import com.example.DistanceCalculator.service.CityService;
import com.example.DistanceCalculator.service.DistanceService;
import com.example.DistanceCalculator.model.response.ResultDistance;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalculateControllerTest {

    private TestRestTemplate testRestTemplate;
    private CityService cityService;
    private DistanceService distanceService;

    private List<String> listCites1 = new ArrayList<>();
    private List<String> listCites2 = new ArrayList<>();

    {
        listCites1.add("TestCity1");
        listCites2.add("TestCity2");
    }

    @Autowired
    public CalculateControllerTest(TestRestTemplate testRestTemplate,
                                   CityService cityService,
                                   DistanceService distanceService) {
        this.testRestTemplate = testRestTemplate;
        this.cityService = cityService;
        this.distanceService = distanceService;
    }

    @BeforeEach
    public void addTestCity() {
        List<City> listOfCity = new ArrayList<>();
        listOfCity.add(new City("TestCity1",46.35,48.04));
        listOfCity.add(new City("TestCity2",53.36,83.76));
        cityService.addCityList(listOfCity);
        List<Distance> distancesList = new ArrayList<>();
        distancesList.add(new Distance("TestCity1","TestCity2",675));
        distanceService.addDistanceList(distancesList);
    }

    @Test
    public void calculateDistanceCrowflight() {
        CalculateParams calculateParams = new CalculateParams("Crowflight",listCites1,listCites2);
        HttpEntity<CalculateParams> httpEntity = new HttpEntity<>(calculateParams);
        ResponseEntity<List<ResultDistance>> response = testRestTemplate.exchange("/calculate", HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<List<ResultDistance>>() {
                });
        List<ResultDistance> list = response.getBody();
        Assertions.assertTrue(response.getStatusCode() == HttpStatus.OK && !list.isEmpty(),"Crowflight list distance is empty");
    }

    @Test
    public void calculateDistanceDistanceMatrix() {
        CalculateParams calculateParams = new CalculateParams("Distance Matrix",listCites1,listCites2);
        HttpEntity<CalculateParams> httpEntity = new HttpEntity<>(calculateParams);
        ResponseEntity<List<ResultDistance>> response = testRestTemplate.exchange("/calculate", HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<List<ResultDistance>>() {
                });
        List<ResultDistance> list = response.getBody();
        Assertions.assertTrue(response.getStatusCode() == HttpStatus.OK && !list.isEmpty(),"Distance Matrix list distance is empty");
    }

    @Test
    public void calculateDistanceAll() {
        CalculateParams calculateParams = new CalculateParams("All",listCites1,listCites2);
        HttpEntity<CalculateParams> httpEntity = new HttpEntity<>(calculateParams);
        ResponseEntity<List<ResultDistance>> response = testRestTemplate.exchange("/calculate", HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<List<ResultDistance>>() {
                });
        List<ResultDistance> list = response.getBody();
        Assertions.assertTrue(response.getStatusCode() == HttpStatus.OK && !list.isEmpty(),"All list distance is empty");
    }

    @AfterEach
    public void deleteTestCity() {
        cityService.deleteCity("TestCity1");
        cityService.deleteCity("TestCity2");
        distanceService.deleteByCites("TestCity1","TestCity2");
    }
}