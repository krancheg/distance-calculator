package com.example.DistanceCalculator.service;

import com.example.DistanceCalculator.model.City;
import com.example.DistanceCalculator.model.Distance;
import com.example.DistanceCalculator.model.JAXB.XmlContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedInputStream;
import java.io.IOException;

@Service
public class UploadServiceImpl implements UploadService {

    private final CityService cityService;

    private final DistanceService distanceService;

    @Autowired
    public UploadServiceImpl(CityService cityService, DistanceService distanceService) {
        this.cityService = cityService;
        this.distanceService = distanceService;
    }

    @Override
    public boolean uploadCitesAndDistancesFile(MultipartFile file) {
        XmlContainer container;
        try {
            BufferedInputStream reader = new BufferedInputStream(file.getInputStream());
            JAXBContext context = JAXBContext.newInstance(City.class, XmlContainer.class, Distance.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            container = (XmlContainer) unmarshaller.unmarshal(reader);
            cityService.addCityList(container.getCity());
            distanceService.addDistanceList(container.getDistance());
        } catch (JAXBException | IOException e) {
            return false;
        }
        return true;
    }

}
