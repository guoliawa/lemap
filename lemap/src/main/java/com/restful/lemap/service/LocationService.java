package com.restful.lemap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restful.lemap.domain.Location;
import com.restful.lemap.repository.LocationRepository;

@Service
public class LocationService {
    
    @Autowired
    private LocationRepository repository;
    
    public List<Location> findAll() {
        return repository.findAll();
    }   
}

