package com.example.DistanceCalculator.controller;

import com.example.DistanceCalculator.model.City;
import com.example.DistanceCalculator.service.CityService;
import com.example.DistanceCalculator.model.response.ResultCity;
import com.example.DistanceCalculator.service.DistanceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceControllerTest {

    private TestRestTemplate restTemplate;
    private final CityService cityService;
    private final DistanceService distanceService;

    @Autowired
    public ServiceControllerTest(TestRestTemplate restTemplate, CityService cityService, DistanceService distanceService) {
        this.restTemplate = restTemplate;
        this.cityService = cityService;
        this.distanceService = distanceService;
    }

    @BeforeEach
    public void addTestCity() {
        List<City> listOfCity = new ArrayList<>();
        listOfCity.add(new City("TestCity1",46.35,48.04));
        cityService.addCityList(listOfCity);
    }

    @Test
    public void getAllCity() {
        ResponseEntity<List<ResultCity>> resp = restTemplate.exchange("/service/all", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ResultCity>>() {
                });
        List<ResultCity> list = resp.getBody();
        Assertions.assertTrue(resp.getStatusCode() == HttpStatus.OK && !list.isEmpty(),"List is empty");
    }

    @Test
    public void uploadCitesAndDistances() {
        String filePath = "CitesDistance.xml";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(filePath));
        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);
        String serverUrl = "/service/upload";
        ResponseEntity<String> resp = restTemplate
                .postForEntity(serverUrl, requestEntity, String.class);
        Assertions.assertTrue(resp.getStatusCode() == HttpStatus.OK
                                        && cityService.getCityByName("TestCity11").getLongitude() == 44.50
                                        && distanceService.getDistanceByCites("TestCity10","TestCity11").getDistance() == 960);
    }

    @AfterEach
    public void deleteTestCity() {
        cityService.deleteCity("TestCity1");
        cityService.deleteCity("TestCity10");
        cityService.deleteCity("TestCity11");
        distanceService.deleteByCites("TestCity10","TestCity11");
    }
}