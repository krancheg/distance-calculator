package com.example.DistanceCalculator.controller;


import com.example.DistanceCalculator.service.CityService;
import com.example.DistanceCalculator.model.response.ResultCity;
import com.example.DistanceCalculator.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {

    private CityService cityService;
    private UploadService uploadService;

    @Autowired
    public ServiceController(CityService cityService, UploadService uploadService) {
        this.cityService = cityService;
        this.uploadService = uploadService;
    }

    @GetMapping("/all")
    public List<ResultCity> getAllCity() {
        List<ResultCity> list = cityService.listOfCites();
        return list;
    }

    @PostMapping("/upload")
    public ResponseEntity uploadCitesAndDistances(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            if (uploadService.uploadCitesAndDistancesFile(file))
                return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
