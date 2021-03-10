package com.example.DistanceCalculator.service;

import com.example.DistanceCalculator.model.City;
import com.example.DistanceCalculator.model.response.ResultCity;

import java.util.List;

public interface CityService {

    void addCityList(List<City> listOfCity);

    City getCityByName(String cityName);

    void deleteCity(String cityName);

    List<ResultCity> listOfCites();

}
