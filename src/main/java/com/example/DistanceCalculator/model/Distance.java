package com.example.DistanceCalculator.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@Table(name = "distance")
public class Distance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "from_city")
    private String fromCity;

    @Column(name = "to_city")
    private String toCity;

    private long distance;

    public Distance(String fromCity, String toCity, long distance) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.distance = distance;
    }

    public Distance() {
    }

    public long getId() {
        return id;
    }

    @XmlTransient
    public void setId(long id) {
        this.id = id;
    }

    public String getFromCity() {
        return fromCity;
    }

    @XmlAttribute
    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    @XmlAttribute
    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public long getDistance() {
        return distance;
    }

    @XmlAttribute
    public void setDistance(long distance) {
        this.distance = distance;
    }

}
