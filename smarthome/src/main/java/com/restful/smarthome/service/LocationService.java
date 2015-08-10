package com.restful.smarthome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restful.smarthome.domain.Location;
import com.restful.smarthome.repository.LocationRepository;

@Service
public class LocationService {
    
    @Autowired
    private LocationRepository repository;
    
    public List<Location> findAll() {
        return repository.findAll();
    }   
}

