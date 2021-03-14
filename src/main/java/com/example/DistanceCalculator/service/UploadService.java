package com.example.DistanceCalculator.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    boolean uploadCitesAndDistancesFile(MultipartFile file);

}
