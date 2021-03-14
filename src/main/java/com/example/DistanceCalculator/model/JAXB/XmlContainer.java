package com.example.DistanceCalculator.model.JAXB;

import com.example.DistanceCalculator.model.City;
import com.example.DistanceCalculator.model.Distance;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "root")
public class XmlContainer {

    @XmlElementWrapper(name = "cites", nillable = true)
    private List city = new ArrayList();

    @XmlElementWrapper(name = "distances", nillable = true)
    private List distance = new ArrayList();

    public List<City> getCity() {
        return city;
    }

    public List<Distance> getDistance() {
        return distance;
    }
}
