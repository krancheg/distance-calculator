package com.example.DistanceCalculator.service;

import com.example.DistanceCalculator.model.response.ResultDistance;

import java.util.List;

public interface CalculateDistance {

    List<ResultDistance> straightDistanceList(List<String> fromCityList, List<String> toCityList);

    List<ResultDistance> matrixDistanceList(List<String> fromCityList, List<String> toCityList);

}
