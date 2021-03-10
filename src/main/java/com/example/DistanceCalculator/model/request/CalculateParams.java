package com.example.DistanceCalculator.model.request;

import java.util.List;

public class CalculateParams {
    private String calculationType;
    private List<String> fromCities;
    private List<String> toCities;

    public CalculateParams(String calculationType, List<String> fromCities, List<String> toCities) {
        this.calculationType = calculationType;
        this.fromCities = fromCities;
        this.toCities = toCities;
    }

    public CalculateParams() {
    }

    public String getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(String calculationType) {
        this.calculationType = calculationType;
    }

    public List<String> getFromCities() {
        return fromCities;
    }

    public void setFromCities(List<String> fromCities) {
        this.fromCities = fromCities;
    }

    public List<String> getToCities() {
        return toCities;
    }

    public void setToCities(List<String> toCities) {
        this.toCities = toCities;
    }
}
