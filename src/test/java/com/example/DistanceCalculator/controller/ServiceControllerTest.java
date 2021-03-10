package com.example.DistanceCalculator.controller;

import com.example.DistanceCalculator.model.City;
import com.example.DistanceCalculator.service.CityService;
import com.example.DistanceCalculator.model.response.ResultCity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceControllerTest {

    private TestRestTemplate restTemplate;
    private final CityService cityService;

    @Autowired
    public ServiceControllerTest(TestRestTemplate restTemplate, CityService cityService) {
        this.restTemplate = restTemplate;
        this.cityService = cityService;
    }

    @BeforeEach
    public void addTestCity() {
        List<City> listOfCity = new ArrayList<>();
        listOfCity.add(new City("TestCity1",46.35,48.04));
        cityService.addCityList(listOfCity);
    }

    @Test
    void testGetAllCity() {
        ResponseEntity<List<ResultCity>> resp = restTemplate.exchange("/service/all", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ResultCity>>() {
                });
        List<ResultCity> list = resp.getBody();
        Assertions.assertTrue(resp.getStatusCode() == HttpStatus.OK && !list.isEmpty(),"List is empty");
    }

    @AfterEach
    public void deleteTestCity() {
        cityService.deleteCity("TestCity1");
    }
}