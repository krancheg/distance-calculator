package com.example.DistanceCalculator.controller;

import com.example.DistanceCalculator.service.CityService;
import com.example.DistanceCalculator.model.response.ResultCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {

    private final CityService cityService;

    @Autowired
    public ServiceController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/all")
    public List<ResultCity> getAllCity() {
        List<ResultCity> list = cityService.listOfCites();
        return list;
    }

}
