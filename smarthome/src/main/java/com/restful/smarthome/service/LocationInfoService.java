package com.restful.smarthome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restful.smarthome.domain.LocationInfo;
import com.restful.smarthome.repository.LocationInfoRepository;

@Service
public class LocationInfoService {
    
    @Autowired
    private LocationInfoRepository repository;
    
    public List<LocationInfo> findAll() {
        return repository.findAll();
    }
    
    public List<LocationInfo> findByUserid(String userid) {
        return repository.findByUseridOrderByTimestampDesc(userid);
    }
    
    public void insert(List<LocationInfo> locationInfos) {
        repository.insert(locationInfos);
    }
    
    public void insert(LocationInfo locationInfo) {
        repository.insert(locationInfo);
    }
}

