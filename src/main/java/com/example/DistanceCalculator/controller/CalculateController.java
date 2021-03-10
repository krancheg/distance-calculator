package com.example.DistanceCalculator.controller;

import com.example.DistanceCalculator.model.request.CalculateParams;
import com.example.DistanceCalculator.service.CalculateDistance;
import com.example.DistanceCalculator.model.response.ResultDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/")
public class CalculateController {

    private final CalculateDistance calculateDistance;

    @Autowired
    public CalculateController(CalculateDistance calculateDistance) {
        this.calculateDistance = calculateDistance;
    }

    @PostMapping("calculate")
    public List<ResultDistance> calculateDistance(@RequestBody CalculateParams calculateParams) {
        List<ResultDistance> list = null;
        switch (calculateParams.getCalculationType()) {
            case "Crowflight":
                list = calculateDistance.straightDistanceList(calculateParams.getFromCities(),
                        calculateParams.getToCities());
                break;
            case "Distance Matrix":
                list = calculateDistance.matrixDistanceList(calculateParams.getFromCities(),
                        calculateParams.getToCities());
                break;
            case "All":
                list = calculateDistance.straightDistanceList(calculateParams.getFromCities(),
                        calculateParams.getToCities());;
                list.addAll(calculateDistance.matrixDistanceList(calculateParams.getFromCities(),
                        calculateParams.getToCities()));
                break;
        }
        if (list == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Calculation type not alloyed");
        if (list.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return list;
    }

}
