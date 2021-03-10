package com.example.DistanceCalculator.service;

import com.example.DistanceCalculator.model.City;
import com.example.DistanceCalculator.repository.CityRepository;
import com.example.DistanceCalculator.model.response.ResultCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public void addCityList(List<City> listOfCity) {
        if (listOfCity.isEmpty())
            return;
        cityRepository.saveAll(listOfCity);
    }

    @Override
    public City getCityByName(String cityName) {
        return cityRepository.findFirstByName(cityName);
    }

    @Override
    public void deleteCity(String cityName) {
        City city = cityRepository.findFirstByName(cityName);
        if (city==null)
            return;
        cityRepository.deleteById(city.getId());
    }

    @Override
    public List<ResultCity> listOfCites() {
        List<ResultCity> resultCityList = new ArrayList<>();
        cityRepository.findAll().forEach((v) -> resultCityList.add(new ResultCity(v.getId(),v.getName())));
        return resultCityList;
    }
}
