package com.restful.smarthome.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restful.smarthome.domain.LocationInfo;
import com.restful.smarthome.domain.PositionInfo;
import com.restful.smarthome.repository.LocationInfoRepository;

@Service
public class LocationInfoService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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

    public String findPositionByUserid(String userid) {
        String PositionsJsonString = "";
        ObjectMapper mapper = new ObjectMapper();
        List<LocationInfo> locations = findByUserid(userid);
        List<PositionInfo> positions = new ArrayList<PositionInfo>();

        for (LocationInfo location : locations) {
            if (location.getLatitude() != 0 || location.getLongitude() != 0) {
                positions.add(location.getPositionInfo());
            }
        }

        try {
            PositionsJsonString = mapper.writeValueAsString(positions);
        } catch (JsonProcessingException e) {
            logger.debug("Processing positions failed!", e.toString());
        }

        return PositionsJsonString;
    }
}
