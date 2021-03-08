package com.example.DistanceCalculator.service;

public interface CalculateDistance {

    long straightDistance(String fromCity, String toCity);

    long matrixDistance(String fromCity, String toCity);

}
