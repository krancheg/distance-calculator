package com.example.DistanceCalculator.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private double latitude;

    private double longitude;

    public City(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public City() {
    }


    public long getId() {
        return id;
    }

    @XmlTransient
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    @XmlElement
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @XmlElement
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
